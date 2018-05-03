package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.apache.commons.lang.ObjectUtils.Null;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.Kitaplar;




public class BookSettingsController implements Initializable{
	
	@FXML
	private TextField txtbookId,txtbookname,txtbookauthor,txtbookpublisher,txtbooknumberpage,txtbookpurchasedate,txtbookmember;
	
	@FXML
	private ImageView imgvbookpicture;
	
	@FXML
	private Button btnBookDelete;
	
    Preferences preference;
    
    List<Kitaplar> books;
    
    Session session;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		preference=Preferences.userNodeForPackage(BookSettingsController.class);
		
        session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Kitaplar> query = builder.createQuery(Kitaplar.class);
	    Root<Kitaplar> root = query.from(Kitaplar.class);
	    query.select(root);
	    Query<Kitaplar> q=session.createQuery(query);
	    books=q.getResultList();
	    session.close();
	    for (Kitaplar book : books) {
	    	
	    	if(preference.getInt("bookID",0)==book.getKitapId())
	    	{
	    		File f=new File(book.getKitapResmi());
				Image image=new Image(f.toURI().toString());
				imgvbookpicture.setImage(image);
				
				txtbookId.setText(String.valueOf(book.getKitapId()));
				txtbookname.setText(book.getKitapAdi());
				txtbookauthor.setText(book.getKitapYazari());
				txtbookpublisher.setText(book.getKitapYayinEvi());
				txtbooknumberpage.setText(String.valueOf(book.getKitapSayfaSayisi()));
				if(book.getKitapAlisTarihi()==null)
				{
					txtbookpurchasedate.setText("");
				}
				else
					txtbookpurchasedate.setText(book.getKitapAlisTarihi().toString());
				if(book.getKitapUyedeMi()==null || book.getKitapUyedeMi().booleanValue()==false)
				txtbookmember.setText("Hayýr");
				else if(book.getKitapUyedeMi().booleanValue()==true)
			    txtbookmember.setText("Evet");
				
	    		
	    	}
	    }
		
		
		
	}
	
	@FXML
	private void BookUpdate()
	{
		session = HibernateUtil.getSessionFactory().openSession();
	   session.beginTransaction();
		for(Kitaplar book : books)
		{
			if(preference.getInt("bookID",0)==book.getKitapId())
			{
				Kitaplar newbook=new Kitaplar();
				newbook.setKitapId(Integer.parseInt(txtbookId.getText()));
				newbook.setKitapAdi(txtbookname.getText());
				newbook.setKitapYazari(txtbookauthor.getText());
				newbook.setKitapYayinEvi(txtbookpublisher.getText());
				newbook.setKitapResmi(book.getKitapResmi());
			
				newbook.setKitapSayfaSayisi(Integer.parseInt(txtbooknumberpage.getText()));
			    session.update(newbook);
			    session.getTransaction().commit();
			    session.clear();
			    
			    JOptionPane.showMessageDialog(null, "Güncelleme iþleminiz gerçekleþmiþtir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		session.clear();
	}
	
	@FXML
	private void BookDelete()
	{
		session = HibernateUtil.getSessionFactory().openSession();
		 
		for(Kitaplar book : books)
		{
			if(preference.getInt("bookID",0)==book.getKitapId())
			{
				Kitaplar newbook=new Kitaplar();
				newbook.setKitapId(book.getKitapId());
				newbook.setKitapAdi(book.getKitapAdi());
				session.delete(newbook);
				session.beginTransaction().commit();
				session.close();
				
			    
			    JOptionPane.showMessageDialog(null, "Silme iþleminiz  gerçekleþmiþtir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
			  
			    try {
	        		Stage primaryStage=new Stage();
	    			AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
	    			Scene scene = new Scene(anchorpane,900,800);
	    	        Stage curentstage=(Stage) btnBookDelete.getScene().getWindow();
	    	        curentstage.hide();
	    			primaryStage.setScene(scene);
	    			primaryStage.show();
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
			}
			
		}
		session.clear();
		
	}
	
	@FXML
	private void BookSettingBack()
	{
		  try {
      		Stage primaryStage=new Stage();
  			AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
  			Scene scene = new Scene(anchorpane,900,800);
  	        Stage curentstage=(Stage) btnBookDelete.getScene().getWindow();
  	        curentstage.hide();
  			primaryStage.setScene(scene);
  			primaryStage.show();
  		} catch(Exception e) {
  			e.printStackTrace();
  		}
		  
	}
	
	
	

}
