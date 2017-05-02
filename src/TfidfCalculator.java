/**
 * Created by vulh on 02/05/2017.
 */
public interface TfidfCalculator {

    /**
     * Document frequency
     *
     * @param term
     * @return
     */
    long getDf(String term);

    /**
     * Term frequency in a document
     *
     * @param term
     * @param docIndex
     * @return
     */
    long getTf(String term, int docIndex);

    /**
     * Tf-idf of a term in a document
     *
     * @param term
     * @param docIndex
     * @return
     */
    double getTfidf(String term, int docIndex);

    /**
     * Average tf-idf of a term across the corpus
     *
     * @param term
     * @return
     */
    double getAverageTfidf(String term);

}
