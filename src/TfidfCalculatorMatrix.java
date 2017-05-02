import java.util.*;

/**
 * Created by vulh on 02/05/2017.
 */
public class TfidfCalculatorMatrix implements TfidfCalculator{

    private long[][] termDocMatrix;
    private List<String> corpus;
    private List<String> uniqueTermsList;
    private List<TermResult> termResults;

    public TfidfCalculatorMatrix(List<String> corpus) {
        this.corpus = corpus;
        calculate();
    }

    @Override
    public long getDf(String term) {
        Optional<TermResult> res = termResults.stream().filter(p -> p.getTerm().equals(term)).findFirst();
        if (res.isPresent()) {
            return res.get().getDf();
        }

        return 0;
    }

    @Override
    public long getTf(String term, int docIndex) {

        int uniqueTermIndex = uniqueTermsList.indexOf(term);

        // If the term appears in the corpus
        if (uniqueTermIndex > -1) {
            return termDocMatrix[uniqueTermIndex][docIndex];
        }

        // Else just return 0
        return 0;
    }

    @Override
    public double getTfidf(String term, int docIndex) {
        double df = getDf(term);
        double idf = df == 0 ? 0 : Math.log10((double)corpus.size() / df);

        return getTf(term, docIndex) * idf;
    }

    @Override
    public double getAverageTfidf(String term) {

        Optional<TermResult> res = termResults.stream().filter(p -> p.getTerm().equals(term)).findFirst();
        if (res.isPresent()) {
            return res.get().getAvgTfidf();
        }

        return 0;
    }

    // Private methods

    /**
     *
     * @throws InterruptedException
     */
    private void calculate() {
        List<List<String>> listDocumentTerms = getTerms(corpus);

        // Once we know all unique terms
        LinkedHashSet<String> uniqueTerms = new LinkedHashSet<>();
        for (List<String> documentTerms : listDocumentTerms) {
            uniqueTerms.addAll(documentTerms);
        }

        uniqueTermsList = new ArrayList<>(uniqueTerms);
        uniqueTerms = null; // Clear up memory

        // Construct matrix
        termDocMatrix = new long[uniqueTermsList.size()][corpus.size()];

        for (int i = 0; i < listDocumentTerms.size(); i++) {
            for (String word : listDocumentTerms.get(i)) {
                int uniqueTermIndex = uniqueTermsList.indexOf(word);
                termDocMatrix[uniqueTermIndex][i] += 1;
            }
        }

        List<TermResult> results = new ArrayList<>();

        for (int i = 0; i < uniqueTermsList.size(); i++) {

            // df
            long sum = Arrays.stream(termDocMatrix[i]).sum();

            long df = Arrays.stream(termDocMatrix[i]).filter(x -> x >= 1).count();
            double idf = df == 0 ? 0 : Math.log10((double)corpus.size() / df);
            DoubleSummaryStatistics averageTfIdf =  Arrays.stream(termDocMatrix[i])
                            .mapToDouble(x -> x * idf).summaryStatistics();

            TermResult tr = new TermResult(uniqueTermsList.get(i), sum, df, averageTfIdf.getAverage());
            results.add(tr);
        }

        this.termResults = results;
    }

    /**
     * Get all terms of a corpus.
     * Each list of strings is a document's terms
     *
     * @param documents
     * @return
     */
    private List<List<String>> getTerms(List<String> documents){

        List<List<String>> documentTermsList = new ArrayList<>();

        for (String doc : documents) {
            documentTermsList.add(Arrays.asList(getTermsArray(doc)));
        }

        return documentTermsList;

    }

    /**
     * Get terms of a string (document)
     *
     * @param content
     * @return
     */
    private String[] getTermsArray(String content) {
        return content.split("\\W+");
    }

    /**
     * Private class
     */
    public class TermResult {

        private String term;
        private long tf;
        private long df;
        private double avgTfidf;

        public TermResult(String term, long tf, long df, double tfidf) {
            this.term = term;
            this.tf = tf;
            this.df = df;
            this.avgTfidf = tfidf;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public long getDf() {
            return df;
        }

        public void setDf(long df) {
            this.df = df;
        }

        public double getAvgTfidf() {
            return avgTfidf;
        }

        public void setAvgTfidf(double avgTfidf) {
            this.avgTfidf = avgTfidf;
        }

        public long getTf() {
            return tf;
        }

        public void setTf(long tf) {
            this.tf = tf;
        }
    }

}
