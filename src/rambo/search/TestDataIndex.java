package rambo.search;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestDataIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
			Directory dir = FSDirectory.open(new File(
					"/Users/rambodu/Documents/workspace/searchDemo/index"));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_47,
					analyzer);
			iwc.setOpenMode(OpenMode.CREATE);

			IndexWriter writer = new IndexWriter(dir, iwc);
			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				System.out.println("adding ");
				// writer.addDocument(doc);
			} else {
				System.out.println("updating ");
				// writer.updateDocument(new Term("path", file.getPath()), doc);
			}

			Document doc = new Document();
			doc.add(new StringField("name", "rambo", Store.YES));
			doc.add(new StringField("title", "¶Å¼ÑÀû", Store.YES));
			doc.add(new IntField("count", 10, Store.NO));
			writer.addDocument(doc);

			doc = new Document();
			doc.add(new StringField("name", "leign", Store.YES));
			doc.add(new StringField("title", "¶Å×ÓÌÛ", Store.YES));
			doc.add(new IntField("count", 5, Store.NO));
			writer.addDocument(doc);

			doc = new Document();
			doc.add(new StringField("name", "ll", Store.YES));
			doc.add(new StringField("title", "¶Å¼Ñ", Store.YES));
			doc.add(new IntField("count", 11, Store.NO));
			writer.addDocument(doc);

			doc = new Document();
			doc.add(new StringField("name", "rr", Store.YES));
			doc.add(new StringField("title", "¼ÑÀû", Store.YES));
			doc.add(new IntField("count", 13, Store.NO));
			writer.addDocument(doc);

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
