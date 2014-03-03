package rambo.search;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class PrefixSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
			Directory dir = FSDirectory.open(new File(
					"/Users/rambodu/Documents/workspace/searchDemo/index"));
			IndexReader reader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			PrefixQuery query = new PrefixQuery(new Term("title", "佳"));
			TopDocs topDocs = searcher.search(query, 5);
			System.out.println("hit total=" + topDocs.totalHits);

			Document doc = null;
			for (ScoreDoc sd : topDocs.scoreDocs) {
				System.out.println("docId=" + sd.doc + ", score=" + sd.score);

				// System.out.println("" + searcher.explain(query, sd.doc));
				doc = searcher.doc(sd.doc);
				System.out.println("doc=" + doc);
				// System.out.println("title=" + doc.get("title"));
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
