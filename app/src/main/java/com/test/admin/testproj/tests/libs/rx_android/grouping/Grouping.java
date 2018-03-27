package com.test.admin.testproj.tests.libs.rx_android.grouping;

import io.reactivex.Observable;

/**
 * Count (group) same articles
 * 1. Only process articles that are supposed to be in stock;
 * 2. Group all articles by the article id, derived from the rfid tag;
 * 3. Count all articles in the group;
 * 4. Emit one ArticleQuantity object per group.
 *
 *
 * We have a bunch of articles which have a unique RFID tag but might be the same article. I might have 10 red t-shirts,
 * for example, which all have a different RFID tag. We want to group these articles by their article ID and
 * then count how many there are in each group. Then, I want to create a new object out of that,
 * emitting an article quantity.
 * First, I want to filter out all the articles that are not supposed to be in stock. This filter operator will create
 * a new observable, will chain it, and will filter out all the articles that are not in stock.
 * Then we’re going to group all the articles by using their RFID tag. This notation means that we are calling
 * a method which takes an article and then returns the tag, the article IDs.
 *
 * groupBy:
 * groupBy function emits not just new values, but it’s actually emitting a new observable of observables.
 * The circles are grouped in a new observable, and all the triangles are also grouped. After we apply this operation,
 * we now have an observable that has a GroupObservable<String, Article>. The string is the key, and the article
 * that I want to figure out, I want to count eventually.
 * Now that we have these nice groups, we can apply flatMap on this GroupObservable. We want to actually
 * process every group. We take in the group, and then map it into a new Object. We have this article,
 * grouped by its idea, and we can create our almost final object, which is the quantity. We get the article ID here
 * and we have a quantity of one. They are still not unique.
 *
 * Reduce:
 * Now we want to sum up every article in this group, and there’s an operator for that called reduce.
 * It takes two Objects and expects you to create one Object out of it. For every ArticleQuantity that I get in here,
 * I’ll just take the ArticleID from the first one, because I know they are the same. They are in the same group,
 * and then I just sum up the quantities.
 * After all this work, we now have an observable that will emit an ArticleQuantity, unique per group,
 * that give me how many of each I have in the group.
 */
public class Grouping {


    public Observable<ArticleQuantity> quantity(Observable<Article> observable) {

        return observable.filter(article -> article.isInStock)
                .groupBy(this::articleIdFromTag)
                // convert each group to a new observable
                // group type: Observable<GroupedObservable<String, Article>>
                .flatMap(group ->  //GroupedObservable<String, Article>
                        // each item in the group is converted to ArticleQuantity
                        group.map(article -> new ArticleQuantity(group.getKey(), 1))
                        // then these items are reduced to a single item per group
                        .reduce((q1, q2) -> new ArticleQuantity(q1.articleId, q1.quantity + q2.quantity))
                        .toObservable()
                );
    }

    /**
     * Combine the list of all rfidTags and the expected ArticleQuantities.
     *
     * We have another situation where we needed to do something more advanced: we want to create an object
     * called the expected stock.
     * We want to combine all the RFID tags that we have seen. That’s a simple thing to do there. We can combine
     * these previous functions that we made. We can convert it to a list, and we can do the same for the quantity.
     * There’s a trick here: we’re going to take the original observable, then we’re going to create that into
     * a connectable observable (method publish()), and then we’re going to tell that observable that we only want
     * to start emitting values once we have two subscribers (autoConnect(2)).
     * The idea of a connectable observable is that you basically broadcast one observable to multiple
     * subscribers at the same time.
     */
    public Observable<ExpectedStock> expectedStock(Observable<Article> articles) {
        Observable<Article> observable = articles.publish().autoConnect(2);

        return Observable.combineLatest(rfIdTags(observable).toList().toObservable(),
                    quantity(observable).toList().toObservable(), ExpectedStock::new);
    }


    private String articleIdFromTag(Article article) {
        return null;
    }

    /**
     * Convert from Article to rfidTag String
     */
    public Observable<String> rfIdTags(Observable<Article> observable) {
        // map = convert an object to another object
        return observable.map(article -> article.rfIdTag);
    }
}
