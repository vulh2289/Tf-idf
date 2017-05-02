import java.util.ArrayList;
import java.util.List;

/**
 * Created by vulh on 02/05/2017.
 */
public class Runner {

    public static void main(String[] args) {

        List<String>  corpus = new ArrayList<>();

        corpus.add("Here's Apple's Next Great Business (AAPL) http://t.co/Crci4dHH39 #InvestWall #AAPL");
        corpus.add("Dear @Apple: I love my iPhone 6 Plus...A lot. It is a great achievement; But why is it the only iPhone I've had that reboots 3x/day? #Fail");
        corpus.add("if I tweet about NANDOS my phone puts it in caps, I like the iPhone six, it knows my favourite restaurant. good phone GG @apple");
        corpus.add("Apple's iPhone Gained Significant Share In The US, Japan, Germany And Great Britain $AAPL #aapl http://t.co/KOUGLOzPZ8");
        corpus.add("RT @schreier: It makes you smarter. Elevate is @apple app of the year! Congratulations @jessepickard and team. http://t.co/r0cgmthoCC");
        corpus.add("RT @tomlindberg99 'Congrats to @Apple, Mary Sotos @WorldResources for Green Power Leadership Awards @GreeneMarktplc ' http://t.co/qjX66tc2sV");
        corpus.add("Amazing customer service today @Apple. UTC store. I worked form the store for several hours on #TBJApodcast while... http://t.co/yNS6oCmO8l");
        corpus.add("Photo: Love IOS 8 @apple @iphone #Love #IOS8 #NoCrop #iphone #young #phone #screen #capturescream #boy... http://t.co/fIDj9FxHDV");
        corpus.add("Major crisis avoided @apple. You guys were able to restore my daughter's #iPhone. Great service, thanks. #5starservice");
        corpus.add("Apple's iPhone 6 Lead-Times Still Longer Than Previous Models $AAPL #aapl http://t.co/bk0tn6WTTm");

        TfidfCalculator tfidfCalculator = new TfidfCalculatorMatrix(corpus);
        String term = "Apple";

        System.out.println("Df:\t" + tfidfCalculator.getDf(term));

        for (int i = 0; i < corpus.size(); i++) {
            System.out.println("Tf in Document " + (i + 1) + ":\t" + tfidfCalculator.getTf(term, i));
            System.out.println("Tf-idf in Document " + (i + 1) + ":\t" + tfidfCalculator.getTfidf(term, i));
        }

        System.out.println("Average Tf-idf:\t" + tfidfCalculator.getAverageTfidf(term));
    }

}
