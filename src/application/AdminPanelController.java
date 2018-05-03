package application;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.hibernate.query.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.Admin;
import resources.Kitaplar;
import resources.Uyeler;

public class AdminPanelController implements Initializable {

	@FXML
	private MenuItem menuitbookadd,menuitbooklist,menuithomepage,menuitbookreturn;
	
	@FXML
	private MenuItem menuitmemberadd,menuitmemberlist;
	
	@FXML
	private Menu menuexit;
	
	@FXML
	private AnchorPane anchorpbookadd,anchorpbooklist,anchorpmemberadd,anchorpmemberlist,ancpmemberupdate,anchorphomepage,anchpbookreturn;
	
	@FXML
	private TextField txtbookaddname,txtbookaddid,txtbookaddauthor,txtbookaddpublisher,txtbookaddnumberpage;
	
	@FXML
	private ImageView imgvbookaddimage;
	
	@FXML
	private FlowPane flowpbooklist;
	
	@FXML
	private ScrollPane scrolpbooklist;
	
	@FXML
	private VBox vbox;
	
	@FXML
	private Label BookName;
	
	@FXML
	private TableView tblvmemberlist;
	
	@FXML
	private TextField txtmembertc,txtmembername,txtmembersurname,txtmemberphone,txtmemberadress,txtmembereposta;
	
	@FXML
	private TextField txtmemberupdatename,txtmemberupdatesurname,txtmemberupdatephone,txtmemberupdateadress,txtmemberupdateeposta;
	
	@FXML
	private Label lblselected1bookname,lblselected2bookname,lblselected3bookname,lblselectedmembertc,lblselectedmembername,lblselectedmembersurname;
	
	@FXML
	private ImageView imgVselected1bookimage,imgVselected2bookimage,imgVselected3bookimage;
	
	@FXML
	private TextField txtbooksearch,txtmembersearch,txtbookreturnmembersearch;
	
    @FXML
    private  Button btnselected2delete,btnselected3delete,btnselected1delete,btnbooksgivemember;
	
    
    @FXML
    private Label lblbookreturnmembername,lblbookreturnmembersurname,lblbookreturn1bookid,lblbookreturn1bookname,lblbookreturn2bookid,lblbookreturn2bookname,lblbookreturn3bookid,lblbookreturn3bookname,lblbookreturnmembertc ,lbllbookrmembername,lblbookrmembersurname,lblbookrmembertc;
	
    @FXML
	private ImageView imgvbookreturn1bookimage,imgvbookreturn2bookimage,imgvbookreturn3bookimage;
    
    @FXML
    private Button btnbookreturn1bookdelete,btnbookreturn2bookdelete,btnbookreturn3bookdelete,btnbookreturn;
    
    @FXML
    private Label lblsmembertc,lblsmembername,lblsmembersurname;
   
    File f=null;
    String path=null;
    JOptionPane x=new JOptionPane();
    
    Preferences preference;
    
   
    
    

  
   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		anchorphomepage.setVisible(true);
	}
	
    @FXML
    public void handleButtonAction(ActionEvent e) 
    {
    
    
    	String mItem = ((MenuItem)e.getSource()).getId();
    	
         
    	if(mItem.equals("menuitbookadd"))
    	{
    		txtbookaddname.setText("");
    		txtbookaddid.setText("");
    		txtbookaddauthor.setText("");
    		txtbookaddpublisher.setText("");
    		txtbookaddnumberpage.setText("");
    		imgvbookaddimage.setImage(null);
    	
    		anchorpbookadd.setVisible(true);
    		anchorpbooklist.setVisible(false);
    		anchorpmemberadd.setVisible(false);
    		anchorpmemberlist.setVisible(false);
    		anchorphomepage.setVisible(false);
    		anchpbookreturn.setVisible(false);
    		
    		
    	}
    	else if(mItem.equals("menuithomepage"))
    	{
    		imgVselected1bookimage.setImage(null);
    		imgVselected2bookimage.setImage(null);
    		imgVselected3bookimage.setImage(null);
    		lblselected2bookname.setText("");
    		lblselected1bookname.setText("");
    		lblselected3bookname.setText("");
    		lblsmembertc.setVisible(false);
    		lblsmembername.setVisible(false);
    		lblsmembersurname.setVisible(false);
    		lblselectedmembertc.setText("");
    		lblselectedmembername.setText("");
    		lblselectedmembersurname.setText("");
    		btnselected1delete.setVisible(false);
    		btnselected2delete.setVisible(false);
    		btnselected3delete.setVisible(false);
    		btnbooksgivemember.setVisible(false);
    		
    		anchorphomepage.setVisible(true);
    		anchorpbookadd.setVisible(false);
    		anchorpbooklist.setVisible(false);
    		anchorpmemberadd.setVisible(false);
    		anchorpmemberlist.setVisible(false);
    		anchpbookreturn.setVisible(false);
    		
    	}
    	
    	else if(mItem.equals("menuitbookreturn"))
    	{
    		lbllbookrmembername.setVisible(false);
    		lblbookrmembersurname.setVisible(false);
    		lblbookreturnmembername.setText("");
    		lblbookreturn1bookid.setText("");
    		lblbookreturn1bookname.setText("");
    		imgvbookreturn1bookimage.setImage(null);
    		btnbookreturn1bookdelete.setVisible(false);
    		btnbookreturn2bookdelete.setVisible(false);
    		imgvbookreturn2bookimage.setImage(null);
    		lblbookreturn2bookname.setText("");
    		lblbookreturn2bookid.setText("");
    		lblbookreturn3bookid.setText("");
    		lblbookreturn3bookname.setText("");
    		imgvbookreturn3bookimage.setImage(null);
    		btnbookreturn3bookdelete.setVisible(false);
    		lblbookrmembertc.setVisible(false);
    		lblbookreturnmembertc.setText("");
    		lblbookreturnmembersurname.setText("");
    		btnbookreturn.setVisible(false);
    		
    		
    		anchorphomepage.setVisible(false);
    		anchorpbookadd.setVisible(false);
    		anchorpbooklist.setVisible(false);
    		anchorpmemberadd.setVisible(false);
    		anchorpmemberlist.setVisible(false);
    		anchpbookreturn.setVisible(true);
    		
    		
    	}
    	else if(mItem.equals("menuitbooklist"))
    	{
    		flowpbooklist.getChildren().clear();
    		anchorpbookadd.setVisible(false);
    		anchorpbooklist.setVisible(true);
    		anchorpmemberadd.setVisible(false);
    		anchorpmemberlist.setVisible(false);
    		anchorphomepage.setVisible(false);
    		anchpbookreturn.setVisible(false);
    		
       
    		flowpbooklist.setHgap(5);
    		flowpbooklist.setVgap(4);
    		
    	    
    	    Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
 	        CriteriaBuilder builder = session.getCriteriaBuilder();
 	        CriteriaQuery<Kitaplar> query = builder.createQuery(Kitaplar.class);
 	        Root<Kitaplar> root = query.from(Kitaplar.class);
 	        query.select(root);
 	        Query<Kitaplar> q=session.createQuery(query);
 	        List<Kitaplar> books=q.getResultList();
 	        session.close();
 	
            
 	        for (Kitaplar book : books) {
 	            
 	        	    vbox=new VBox();
 	     	        BookName=new Label();
 	     	        BookName.setPadding(new Insets(0,0,0,10));
 	        	    File f=new File(book.getKitapResmi());
					Image image=new Image(f.toURI().toString());
					ImageView imageview=new ImageView();
					imageview.setFitHeight(270);
					imageview.setFitWidth(160);
					imageview.setImage(image);
					BookName.setText(book.getKitapAdi());
					vbox.getChildren().addAll(imageview,BookName);
					vbox.setMargin(BookName,new Insets(10,10,10,10));
					vbox.setMargin(imageview,new Insets(15,15,5,15));
					 imageview.setOnMouseClicked(new EventHandler<MouseEvent>(){
						   
				            @Override
				            public void handle(MouseEvent event) {
				  
				          
									// TODO Auto-generated method stub
									try {
										
										
						        		Stage primaryStage=new Stage();
						        		preference=Preferences.userNodeForPackage(BookSettingsController.class);
						    	        preference.putInt("bookID", book.getKitapId());
						        	    AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("BookSettings.fxml"));
						    	        Scene scene = new Scene(anchorpane,600,600);
						    	        ((Node) event.getSource()).getScene().getWindow().hide();
						    	        primaryStage.setScene(scene);
						    			primaryStage.show();
						    		
						    		} catch(Exception e) {
						    			e.printStackTrace();
						    			
										
						            }
						    		
									
				            }
				       
				        });
				     
                        


				   
				  
				   
			       
					flowpbooklist.getChildren().add(vbox);
 	        }
    		scrolpbooklist.setContent(flowpbooklist);
    		
           books.clear();
           
           
    		
    	}
    	if(mItem.equals("menuitmemberadd"))
    	{
    		
    		txtmembertc.setText("");
    		txtmembername.setText("");
    		txtmembersurname.setText("");
    		txtmemberphone.setText("");
    		txtmemberadress.setText("");
    		txtmembereposta.setText("");
    		anchorpbookadd.setVisible(false);
    		anchorpbooklist.setVisible(false);
    		anchorpmemberadd.setVisible(true);
    		anchorpmemberlist.setVisible(false);
    		anchorphomepage.setVisible(false);
    		anchpbookreturn.setVisible(false);
    		
    		
    	}
    	if(mItem.equals("menuitmemberlist"))
    	{
    		
    		txtmemberupdateeposta.setText("");
    		txtmemberupdateadress.setText("");
    		txtmemberupdatename.setText("");
    		txtmemberupdatephone.setText("");
    		txtmemberupdatesurname.setText("");
    		ancpmemberupdate.setVisible(false);
    		tblvmemberlist.getItems().clear();
    		tblvmemberlist.getColumns().clear();
    		anchorpbookadd.setVisible(false);
    		anchorpbooklist.setVisible(false);
    		anchorpmemberadd.setVisible(false);
    		anchorpmemberlist.setVisible(true);
    		anchorphomepage.setVisible(false);
    		anchpbookreturn.setVisible(false);
    		
    		
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
 	        CriteriaBuilder builder = session.getCriteriaBuilder();
 	        CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
 	        Root<Uyeler> root = query.from(Uyeler.class);
 	        query.select(root);
 	        Query<Uyeler> q=session.createQuery(query);
 	        List<Uyeler> members=q.getResultList();
 	        session.close();
 	   	 
 	     
 	    // tblvmemberlist.setEditable(true);
    	 TableColumn membertc = new TableColumn("Üye TC");
    	 membertc.setMinWidth(100);
    	 membertc.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, Long>("uyeTc"));
    	 membertc.setStyle("-fx-alignment: CENTER;");
  
    	 TableColumn membername = new TableColumn("Ýsim");
    	 membername.setMinWidth(100);
    	 membername.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeAdi")); 
    	 membername.setStyle("-fx-alignment: CENTER;");
    	
    	 TableColumn membersurname = new TableColumn("SoyÝsim");
    	 membersurname.setMinWidth(100);
    	 membersurname.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeSoyadi")); 
    	 membersurname.setStyle("-fx-alignment: CENTER;");

    	 
    	 TableColumn memberacountdate = new TableColumn("Kayýt Tarihi");
    	 memberacountdate.setMinWidth(100);
    	 memberacountdate.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeBaþlangicTarihi")); 
    	 memberacountdate.setStyle("-fx-alignment: CENTER;");
    	 
     	TableColumn memberphone = new TableColumn("Telefon Numarasý");
     	memberphone.setMinWidth(100);
     	memberphone.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeTelefon")); 
     	memberphone.setStyle("-fx-alignment: CENTER;");
     	
     	TableColumn membeposta = new TableColumn("E Posta");
     	membeposta.setMinWidth(100);
     	membeposta.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeEMail")); 
     	memberphone.setStyle("-fx-alignment: CENTER;");
    	 
     	 TableColumn memberadress = new TableColumn("Adres");
     	memberadress.setMinWidth(150);
     	memberadress.setCellValueFactory(
                 new PropertyValueFactory<Uyeler, String>("uyeAdres"));
     	memberadress.setStyle("-fx-alignment: CENTER;");
     
    	 
    	 tblvmemberlist.getColumns().addAll(membertc, membername,membersurname,memberacountdate,memberphone,membeposta,memberadress);
 	        
            // for(Uyeler member:members)
             //{
            	   ObservableList<Uyeler>u=FXCollections.observableArrayList(members);
              	    tblvmemberlist.setItems(u);
              	    
              	 


            // }
 	        
    		
    	}
    
    			
    }
    
    @FXML
    private void Exitt()
    {
    	Stage primaryStage=new Stage();
		AnchorPane anchorpane=null;
		try {
			anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scene scene = new Scene(anchorpane,500,500);
		Stage curentstage=(Stage) btnbooksgivemember.getScene().getWindow();
		curentstage.hide();
		primaryStage.setScene(scene);
		primaryStage.show();
    }
     @FXML
    public void BookAdd()
    {
    	   Session session = HibernateUtil.getSessionFactory().openSession();
 		    session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Kitaplar> query = builder.createQuery(Kitaplar.class);
	        Root<Kitaplar> root = query.from(Kitaplar.class);
	        query.select(root);
	        Query<Kitaplar> q=session.createQuery(query);
	        List<Kitaplar> books=q.getResultList();
	        session.close();
    	 
	        int sayac=0;
    	 for(Kitaplar book:books)
    	 {
    		 
    		 if(book.getKitapId()==Integer.parseInt(txtbookaddid.getText()))
    		 {
    			 sayac=1;
    			 JOptionPane.showMessageDialog(null,"Kütüphanede bu kitap ID'sine sahip bir kitap bulunmaktadýr.Lütfen ID'sini deðiþtiriniz ", "Bilgilendirme Mesajý", JOptionPane.INFORMATION_MESSAGE
    					 );
    		 }
    	 }
    	 if(sayac==0)
    	 {
    		 session = HibernateUtil.getSessionFactory().openSession();
        	 
   	      Kitaplar kitap=new Kitaplar();
   	      kitap.setKitapAdi(txtbookaddname.getText());
   	      kitap.setKitapId(Integer.parseInt(txtbookaddid.getText()));
   	      kitap.setKitapSayfaSayisi(Integer.parseInt(txtbookaddnumberpage.getText()));
   	      kitap.setKitapYazari(txtbookaddauthor.getText());
   	      kitap.setKitapYayinEvi(txtbookaddpublisher.getText());
   	      kitap.setKitapResmi(path);
   	      session.save(kitap);
   	      session.beginTransaction().commit();
   	      session.close();
   	      
   	     JOptionPane.showMessageDialog(null, "Kitap baþarýlý bir þekilde yüklenmiþtir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
   	   	txtbookaddname.setText("");
   		txtbookaddid.setText("");
   		txtbookaddauthor.setText("");
   		txtbookaddpublisher.setText("");
   		txtbookaddnumberpage.setText("");
   		imgvbookaddimage.setImage(null);
    	 }
    	   
	      
    }
     
     @FXML
     public void BookImageAdd()
     {
    	 JFileChooser fileChooser=new JFileChooser();
    	 fileChooser.showOpenDialog(fileChooser);
    	 f=fileChooser.getSelectedFile();
        // f.renameTo(new File("C:\\Users\\ACER\\eclipse-workspace\\kutuphaneson\\src\\application\\picture\\"+f.getName()));
         path=f.getAbsolutePath();
         Image image = new Image(f.toURI().toString());
         imgvbookaddimage.setImage(image);
    	 // JOptionPane.showMessageDialog(null,f.getAbsolutePath(),"Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	
     }	
     
     @FXML
     private void MemberAdd()
     {
    	 Date simdikiZaman = new Date();
        
         Session session = HibernateUtil.getSessionFactory().openSession();
    	 session.beginTransaction();
    	 Uyeler member=new Uyeler();
    	 member.setUyeTc(Long.valueOf(txtmembertc.getText()));
    	 member.setUyeAdi(txtmembername.getText());
    	 member.setUyeSoyadi(txtmembersurname.getText());
    	 member.setUyeTelefon(txtmemberphone.getText());
    	 member.setUyeAdres(txtmemberadress.getText());
    	 member.setUyeEMail(txtmembereposta.getText());
    	 member.setUyeBaþlangicTarihi(simdikiZaman);
    	 session.save(member);
    	 session.getTransaction().commit();
	     session.close();
	     JOptionPane.showMessageDialog(null,"Üye Eklenmiþtir","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
	     
	 	txtmembertc.setText("");
		txtmembername.setText("");
		txtmembersurname.setText("");
		txtmemberphone.setText("");
		txtmemberadress.setText("");
		txtmembereposta.setText("");
    	 
    	 
    	 
     }
     
     @FXML
     private  void MemberUpdate()
     {

    	 //Uyeler member = (Uyeler) tblvmemberlist.getSelectionModel().getSelectedItem();
    	 ancpmemberupdate.setVisible(true);
    	
     }
     
     @FXML
     private  void MemberDelete()
     {
    	    Session session = HibernateUtil.getSessionFactory().openSession();
  	        session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
	        Root<Uyeler> root = query.from(Uyeler.class);
	        query.select(root);
	        Query<Uyeler> q=session.createQuery(query);
	        List<Uyeler> members=q.getResultList();
	        session.close();
    	 Uyeler member = (Uyeler) tblvmemberlist.getSelectionModel().getSelectedItem();
    	 for(Uyeler m:members)
    	 {
    		 if(member.getUyeTc().equals(m.getUyeTc()))
    		 {
    			Iterator iter=member.getKitaplars().iterator();
      		    
      		   while(iter.hasNext())
      		   {
      			  System.out.println(((Kitaplar)iter.next()).getKitapAdi());
      		   }
    			 if(member.getKitaplars().isEmpty())
    			 {
    				 
    				 
    				 
    				 
    			 session = HibernateUtil.getSessionFactory().openSession();
    			 session.delete(m);
    			 session.beginTransaction().commit();
 	    		 session.close();
 	    		 
 	    		 JOptionPane.showMessageDialog(null, "Silme  iþleminiz gerçekleþmiþtir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
 	    		 
 	    	     tblvmemberlist.getItems().clear();
	    	    session = HibernateUtil.getSessionFactory().openSession();
	     	    session.beginTransaction();
	 	        CriteriaBuilder builder1 = session.getCriteriaBuilder();
	 	        CriteriaQuery<Uyeler> query1 = builder1.createQuery(Uyeler.class);
	 	        Root<Uyeler> root1 = query1.from(Uyeler.class);
	 	        query1.select(root1);
	 	        Query<Uyeler> q1=session.createQuery(query1);
	 	        List<Uyeler> members1=q1.getResultList();
	 	        session.close();
		        ObservableList<Uyeler>u=FXCollections.observableArrayList(members1);
		        tblvmemberlist.setItems(u);
    		     }
    			 else
        			 JOptionPane.showMessageDialog(null, "Üyede kitap bulunduðu için silme iþlemi gerçekleþtirilemiyor. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    			 
    		 }
    		 
    		 
    			 
    			 
    		 }
    	 
    	 
    	 }
    	 
     
     
     @FXML
     private  void MemberUpdate2()
     {
    	 
    	    Session session = HibernateUtil.getSessionFactory().openSession();
    	    session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
	        Root<Uyeler> root = query.from(Uyeler.class);
	        query.select(root);
	        Query<Uyeler> q=session.createQuery(query);
	        List<Uyeler> members=q.getResultList();
	        session.close();
	       
    	    Uyeler member = (Uyeler) tblvmemberlist.getSelectionModel().getSelectedItem();
    	    for(Uyeler m:members)
    	    {
    	    	System.out.println(m.getUyeTc().toString());

                 if(member.getUyeTc().equals(m.getUyeTc()))
    	    	{
    	    		//Session nsession = HibernateUtil.getSessionFactory().openSession();
    	    		Uyeler memberinf=new Uyeler();
    	    		
    	    		memberinf.setUyeTc(m.getUyeTc());
    	    		if(txtmemberupdatename.getText().equals(""))
        	    		memberinf.setUyeAdi(m.getUyeAdi());
        	    		else
        	    	    memberinf.setUyeAdi(txtmemberupdatename.getText());
    	    		System.out.println("2");
    	    		
    	    		if(txtmemberupdatesurname.getText().equals(""))
        	    		memberinf.setUyeSoyadi(m.getUyeSoyadi());
        	    		else
        	    	    memberinf.setUyeSoyadi(txtmemberupdatesurname.getText());
    	    		System.out.println("3");
    	    		
    	    		if(txtmemberupdatephone.getText().equals(""))
        	    		memberinf.setUyeTelefon(m.getUyeTelefon());
        	    		else
        	    	    memberinf.setUyeTelefon(txtmemberupdatephone.getText());
    	    		
    	    		if(txtmemberupdateadress.getText().equals(""))
        	    		memberinf.setUyeAdres(m.getUyeAdres());
        	    		else
        	    	    memberinf.setUyeAdres(txtmemberupdateadress.getText());
    	    		
    	    		if(txtmemberupdateeposta.getText().equals(""))
        	    		memberinf.setUyeEMail(m.getUyeEMail());
        	    		else
        	    	    memberinf.setUyeEMail(txtmemberupdateeposta.getText());
    	    		
    	    		memberinf.setUyeBaþlangicTarihi(m.getUyeBaþlangicTarihi());
    	    		
    	    		
    	    		//nsession.beginTransaction();
    	    	    session = HibernateUtil.getSessionFactory().openSession();
    	    		session.update(memberinf);
    	    		session.beginTransaction().commit();
    	    		session.close();
    	    		JOptionPane.showMessageDialog(null, "Güncelleme iþleminiz gerçekleþmiþtir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	    		
    	    		
    	    		
    	    		
    	    		tblvmemberlist.getItems().clear();
    	    		 session = HibernateUtil.getSessionFactory().openSession();
    	     	    session.beginTransaction();
    	 	        CriteriaBuilder builder1 = session.getCriteriaBuilder();
    	 	        CriteriaQuery<Uyeler> query1 = builder1.createQuery(Uyeler.class);
    	 	        Root<Uyeler> root1 = query1.from(Uyeler.class);
    	 	        query1.select(root1);
    	 	        Query<Uyeler> q1=session.createQuery(query1);
    	 	        List<Uyeler> members1=q1.getResultList();
    	 	        session.close();
    		        ObservableList<Uyeler>u=FXCollections.observableArrayList(members1);
    		        tblvmemberlist.setItems(u);
    		        
    		        txtmemberupdateeposta.setText("");
    	    		txtmemberupdateadress.setText("");
    	    		txtmemberupdatename.setText("");
    	    		txtmemberupdatephone.setText("");
    	    		txtmemberupdatesurname.setText("");
    	    		ancpmemberupdate.setVisible(false);
    		       
    	    		
    	    		
    	    		  
    	    	}
    	    }
    	 
    	
    	
     }
     
     @FXML
     private void SearchOperations(KeyEvent ke)
     {
          
    	 if (ke.getCode()==KeyCode.ENTER)
         {
    	    String mItem=((TextField)ke.getSource()).getId();
            if(mItem.equals("txtbooksearch"))
            {
            	String booknameandid=txtbooksearch.getText();
            	
            	Session session = HibernateUtil.getSessionFactory().openSession();
        		session.beginTransaction();
     	        CriteriaBuilder builder = session.getCriteriaBuilder();
     	        CriteriaQuery<Kitaplar> query = builder.createQuery(Kitaplar.class);
     	        Root<Kitaplar> root = query.from(Kitaplar.class);
     	        query.select(root);
     	        Query<Kitaplar> q=session.createQuery(query);
     	        List<Kitaplar> books=q.getResultList();
     	        session.close();
     	       for(Kitaplar book:books)
     	       {
     	    	 
     	    	   if(book.getKitapAdi().equals(booknameandid))
     	    	   {
     	    		   System.out.println("m1");
     	    		   if(book.getKitapUyedeMi()==null || book.getKitapUyedeMi().booleanValue()==false)
     	    		   {
     	    			  System.out.println("m2");
     	    			   if(lblselected1bookname.getText()=="")
     	    			   {
     	    			   lblselected1bookname.setText(book.getKitapAdi().toString());
     	    			    File f=new File(book.getKitapResmi());
     						Image image=new Image(f.toURI().toString());
     						imgVselected1bookimage.setImage(image);
     						btnselected1delete.setVisible(true);
     						txtbooksearch.setText("");
     	    			   }
     	    			   else if(lblselected2bookname.getText()=="")
     	    			   {
     	    				lblselected2bookname.setText(book.getKitapAdi().toString());
       	    			    File f=new File(book.getKitapResmi());
       						Image image=new Image(f.toURI().toString());
       						imgVselected2bookimage.setImage(image);
       						btnselected2delete.setVisible(true);
       						txtbooksearch.setText("");
     	    			   }
     	    			  else if(lblselected3bookname.getText()=="")
    	    			   {
    	    				lblselected3bookname.setText(book.getKitapAdi().toString());
      	    			    File f=new File(book.getKitapResmi());
      						Image image=new Image(f.toURI().toString());
      						imgVselected3bookimage.setImage(image);
      						btnselected3delete.setVisible(true);
      						txtbooksearch.setText("");
    	    			   }
     	    		   }
     	    		   else
     	    		   {
     	    			  JOptionPane.showMessageDialog(null, "Aradýðýnýz kitap"+ book.getUyeler().getUyeTc() +" TC numarasýna sahip "+book.getUyeler().getUyeAdi()+" adlý üyededir.","Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
     	    			 txtbooksearch.setText("");
     	    		   }
     	    			  
     	    		   
     	    		  
     	    		   continue;
     	    		   //else
     	    			 // JOptionPane.showMessageDialog(null, "aramýþ olduðunuz kitap üyededir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
     	    	   }
     	    	  // else
     	    		 // JOptionPane.showMessageDialog(null, "kütüphanede bu isimde bir kitap bulunmamaktadýr. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
     	    		  
     	    	   else if(String.valueOf(book.getKitapId()).equals(booknameandid))
    	    	   {
    	    		   if(book.getKitapUyedeMi()==null || book.getKitapUyedeMi().booleanValue()==false)
    	    		   {
    	    			  
    	    			   if(lblselected1bookname.getText()=="")
    	    			   {
    	    			   lblselected1bookname.setText(book.getKitapAdi().toString());
    	    			    File f=new File(book.getKitapResmi());
    						Image image=new Image(f.toURI().toString());
    						imgVselected1bookimage.setImage(image);
    						btnselected1delete.setVisible(true);
    						txtbooksearch.setText("");
    	    			   }
    	    			   else if(lblselected2bookname.getText()=="")
    	    			   {
    	    				lblselected2bookname.setText(book.getKitapAdi().toString());
      	    			    File f=new File(book.getKitapResmi());
      						Image image=new Image(f.toURI().toString());
      						imgVselected2bookimage.setImage(image);
      						btnselected2delete.setVisible(true);
      						txtbooksearch.setText("");
    	    			   }
    	    			  else if(lblselected3bookname.getText()=="")
   	    			   {
   	    				    lblselected3bookname.setText(book.getKitapAdi().toString());
     	    			    File f=new File(book.getKitapResmi());
     						Image image=new Image(f.toURI().toString());
     						imgVselected3bookimage.setImage(image);
     						btnselected3delete.setVisible(true);
     						txtbooksearch.setText("");
   	    			   }
    	    		   }
    	    		   else
    	    		   {
    	    			   JOptionPane.showMessageDialog(null, "Aradýðýnýz kitap   "+ book.getUyeler().getUyeTc() +"  TC numarasýna sahip  "+book.getUyeler().getUyeAdi()+"  adlý üyededir.","Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
    	    			   txtbooksearch.setText("");
    	    		   }
     	    			  
     	    		   
    	    		   //else
    	    			 // JOptionPane.showMessageDialog(null, "aramýþ olduðunuz kitap üyededir. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	    	   }
    	    	  // else
    	    		 // JOptionPane.showMessageDialog(null, "kütüphanede bu isimde bir kitap bulunmamaktadýr. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	    		   
     	       }
            	
            }
            if(mItem.equals("txtmembersearch"))
            {
              String membertc=txtmembersearch.getText();
              Session session = HibernateUtil.getSessionFactory().openSession();
      	      session.beginTransaction();
  	          CriteriaBuilder builder = session.getCriteriaBuilder();
  	          CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
  	          Root<Uyeler> root = query.from(Uyeler.class);
  	          query.select(root);
  	          Query<Uyeler> q=session.createQuery(query);
  	          List<Uyeler> members=q.getResultList();
  	          session.close();
  	          for(Uyeler member:members)
  	          {
  	        	  
  	        	  if(membertc.equals(member.getUyeTc().toString()))
  	        	  {
  	        		  lblsmembertc.setVisible(true);
  	        		  lblsmembername.setVisible(true);
  	        		  lblsmembersurname.setVisible(true);
  	        		  btnbooksgivemember.setVisible(true);
  	        		  lblselectedmembertc.setText(member.getUyeTc().toString());
  	        		  lblselectedmembername.setText(member.getUyeAdi());
  	        		  lblselectedmembersurname.setText(member.getUyeSoyadi());
  	        		  txtmembersearch.setText("");
  	        		  
  	        	  }
  	          }
              
            }
         }
     }
     
       @FXML
         private void SelectedBookDelete(ActionEvent e)
         {
    	   String mItem=((Button)e.getSource()).getId();
    	   if(mItem.equals("btnselected1delete"))
    	   {
    		   lblselected1bookname.setText("");
    		   imgVselected1bookimage.setImage(null);
    		   btnselected1delete.setVisible(false);
    	   }
    	   if(mItem.equals("btnselected2delete"))
    	   {
    		   lblselected2bookname.setText("");
    		   imgVselected2bookimage.setImage(null);
    		   btnselected2delete.setVisible(false);
    	   }
    	   if(mItem.equals("btnselected3delete"))
    	   {
    		   lblselected3bookname.setText("");
    		   imgVselected3bookimage.setImage(null);
    		   btnselected3delete.setVisible(false);
    	   }
    	   if(mItem.equals("btnbookreturn1bookdelete"))
    	   {
    		   lblbookreturn1bookid.setText("");
    		   imgvbookreturn1bookimage.setImage(null);
    		   lblbookreturn1bookname.setText("");
    		   btnbookreturn1bookdelete.setVisible(false);
    	   }
    	   
    	   if(mItem.equals("btnbookreturn2bookdelete"))
    	   {
    		   lblbookreturn2bookid.setText("");
    		   imgvbookreturn2bookimage.setImage(null);
    		   lblbookreturn2bookname.setText("");
    		   btnbookreturn2bookdelete.setVisible(false);
    	   }
    	   
    	   if(mItem.equals("btnbookreturn3bookdelete"))
    	   {
    		   lblbookreturn3bookid.setText("");
    		   imgvbookreturn3bookimage.setImage(null);
    		   lblbookreturn3bookname.setText("");
    		   btnbookreturn3bookdelete.setVisible(false);
    	   }
         }
       
       @FXML
       private void BooksGiveMember()
       {
    	   if(lblselected1bookname.getText()!="" || lblselected2bookname.getText()!="" || lblselected3bookname.getText()!="")
    	   {
    		   if(lblselectedmembertc.getText()!="")
    		   {
    			      Session session = HibernateUtil.getSessionFactory().openSession();
    	      	      session.beginTransaction();
    	  	          CriteriaBuilder builder = session.getCriteriaBuilder();
    	  	          CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
    	  	          Root<Uyeler> root = query.from(Uyeler.class);
    	  	          query.select(root);
    	  	          Query<Uyeler> q=session.createQuery(query);
    	  	          List<Uyeler> members=q.getResultList();
    	  	          
    	  	         CriteriaQuery<Kitaplar> query1 = builder.createQuery(Kitaplar.class);
  	  	             Root<Kitaplar> root1 = query1.from(Kitaplar.class);
  	  	             query1.select(root1);
  	  	             Query<Kitaplar> q1=session.createQuery(query1);
  	  	             List<Kitaplar> books=q1.getResultList();
  	  	             session.close();
    	  	          
    	  	          for(Uyeler member:members)
    	  	          {
    	  	        	  if(lblselectedmembertc.getText().equals(member.getUyeTc().toString()))
    	  	        	  {
    	  	        		  HashSet<Kitaplar>booklist=new HashSet<Kitaplar>();
    	  	        		  for(Kitaplar b:books)
    	  	        		  {
    	  	        			Date today=new Date();
    	  	        			session = HibernateUtil.getSessionFactory().openSession();
    	  	        			if(lblselected1bookname.getText()!="" && b.getKitapAdi().equals(lblselected1bookname.getText()))
       	  	                 {
       	  	                	/* Kitaplar book=new Kitaplar();
       	  	                	 book.setKitapAdi(lblselected1bookname.getText());
       	  	                	 book.setKitapUyedeMi(true);
       	  	                	 books.add(book);*/
    	  	        	         b.setKitapUyedeMi(true);
    	  	        	         
    	  	        	         b.setKitapAlisTarihi(today);
    	  	        	         b.setUyeler(member);
    	  	        	         session.update(b);
    	  	        	         
    	  	        	         session.beginTransaction().commit();
    	  	        	         booklist.add(b);
    	  	        	         
    	  	        			 
       	  	                         
       	  	                 }
       	  	                 if(lblselected2bookname.getText()!="" && b.getKitapAdi().equals(lblselected2bookname.getText()))
       	  	                 {
       	  	                	 /*Kitaplar book=new Kitaplar();
       	  	                	 book.setKitapAdi(lblselected2bookname.getText());
       	  	                	 book.setKitapUyedeMi(true);
       	  	                	 books.add(book);*/
       	  	                 b.setKitapUyedeMi(true);
       	  	                 b.setKitapAlisTarihi(today);
 	        	             b.setUyeler(member);
	  	        	         session.update(b);
	  	        	         session.beginTransaction().commit();
	  	        	          booklist.add(b);
       	  	                	 
       	  	                 }
       	  	                 if(lblselected3bookname.getText()!="" && b.getKitapAdi().equals(lblselected3bookname.getText()))
       	  	                 {
       	  	                	/* Kitaplar book=new Kitaplar();
       	  	                	 book.setKitapAdi(lblselected3bookname.getText());
       	  	                	 book.setKitapUyedeMi(true);
       	  	                	 books.add(book);*/
       	  	                 b.setKitapUyedeMi(true);
       	  	                 b.setKitapAlisTarihi(today);
 	        	             
       	  	                 b.setUyeler(member);
	  	        	         session.update(b);
	  	        	         session.beginTransaction().commit();
	  	        	         booklist.add(b); 
       	  	                 }
       	  	                 
       	  	               
    	  	        		  }
    	  	        		  session.close();
    	  	        		 member.setKitaplars(booklist);
    	  	        		
    	  	        	 	imgVselected1bookimage.setImage(null);
    	  	      		 imgVselected2bookimage.setImage(null);
    	  	      		 imgVselected3bookimage.setImage(null);
    	  	      		lblselected2bookname.setText("");
    	  	      		lblselected1bookname.setText("");
    	  	      		lblselected3bookname.setText("");
    	  	      		lblsmembertc.setText("");
    	  	      		lblsmembername.setText("");
    	  	      		lblsmembersurname.setText("");
    	  	      		lblselectedmembertc.setText("");
    	  	      		lblselectedmembername.setText("");
    	  	      		lblselectedmembersurname.setText("");
    	  	      		btnselected1delete.setVisible(false);
    	  	      		btnselected2delete.setVisible(false);
    	  	      		btnselected3delete.setVisible(false);
    	  	      		btnbooksgivemember.setVisible(false);
    	  	        		 MailSend(member.getUyeEMail(),booklist);
    	  	        		 JOptionPane.showMessageDialog(null,"Üyeye kitap atama iþlemi gerçekleþmiþtir ve mail gönderilmiþtir","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	  	        		 break;
    	  	        		
    	  	        	  }
    	  	          }
                 
                 
                 
    			   
    		   }
    		   else
    			   JOptionPane.showMessageDialog(null, "Lütfen üye seçiniz","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
    	   }
    	   else
			   JOptionPane.showMessageDialog(null, "Lütfen kitap seçiniz","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
       }
       
       
       
      @FXML
      private void BookReturnSearchOperations(KeyEvent ke)
      {
    	  if(ke.getCode()==KeyCode.ENTER)
    	  {
    		  System.out.println("zlkxmvcb");
    		  Session session = HibernateUtil.getSessionFactory().openSession();
      	      session.beginTransaction();
  	          CriteriaBuilder builder = session.getCriteriaBuilder();
  	          CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
  	          Root<Uyeler> root = query.from(Uyeler.class);
  	          query.select(root);
  	          Query<Uyeler> q=session.createQuery(query);
  	          List<Uyeler> members=q.getResultList();
  	          session.close();
  	           int sayac=0;
  	          for(Uyeler member:members)
  	          {
  	        	  System.out.println(member.getUyeTc().toString());
  	        	  if(txtbookreturnmembersearch.getText().equals(member.getUyeTc().toString()))
  	        	  {
  	        		  sayac=1;
  	        		  lblbookreturnmembername.setText(member.getUyeAdi());
  	        		  lblbookreturnmembersurname.setText(member.getUyeSoyadi());
  	        		  lblbookreturnmembertc.setText(String.valueOf(member.getUyeTc()));
  	        		  lblbookrmembertc.setVisible(true);
  	        		  lbllbookrmembername.setVisible(true);
  	        		  lblbookrmembersurname.setVisible(true);
  	        		  
  	        		  Set<Kitaplar>booklist= member.getKitaplars();
  	        		  Iterator itera=booklist.iterator();
  	        		while(itera.hasNext())
  	        		  {
  	        			Kitaplar k=(Kitaplar)itera.next();
  	        			if(lblbookreturn1bookname.getText()=="")
  	        			{
  	        
  	        				lblbookreturn1bookid.setText(String.valueOf(k.getKitapId()));
  	        				lblbookreturn1bookname.setText(k.getKitapAdi());
  	        				File f=new File(k.getKitapResmi());
  	        				Image image=new Image(f.toURI().toString());
     						imgvbookreturn1bookimage.setImage(image);
     						btnbookreturn1bookdelete.setVisible(true);
     						btnbookreturn.setVisible(true);
     								
  	        			}
  	        			
  	        			else if(lblbookreturn2bookname.getText()=="")
  	        			{
  	        				lblbookreturn2bookid.setText(String.valueOf(k.getKitapId()));
  	        				lblbookreturn2bookname.setText(k.getKitapAdi());
  	        				File f=new File(k.getKitapResmi());
  	        				Image image=new Image(f.toURI().toString());
     						imgvbookreturn2bookimage.setImage(image);
     						btnbookreturn2bookdelete.setVisible(true);
  	        				
  	        			}
  	        			else if(lblbookreturn3bookname.getText()=="")
  	        			{
  	        				lblbookreturn3bookid.setText(String.valueOf(k.getKitapId()));
  	        				lblbookreturn3bookname.setText(k.getKitapAdi());
  	        				File f=new File(k.getKitapResmi());
  	        				Image image=new Image(f.toURI().toString());
     						imgvbookreturn3bookimage.setImage(image);
     						btnbookreturn3bookdelete.setVisible(true);
  	        				
  	        			}
  	        			
  	        			
  	        			  
  	        		  }
  	        		
  	        		
  	        	     
  	        	  }
  	        	 
  	        		  
  	          }
  	         
  	          
  	        if(sayac==0)
	        		JOptionPane.showMessageDialog(null, "Kütüphanede kayýtlý üye bulunamadý.","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);  
  	          txtbookreturnmembersearch.setText("");
  	     
  	          
  	          
    	  }
      }
      
      @FXML
      private void BookReturn()
      {
    	      Session session = HibernateUtil.getSessionFactory().openSession();
  	          session.beginTransaction();
	          CriteriaBuilder builder = session.getCriteriaBuilder();
	          CriteriaQuery<Uyeler> query = builder.createQuery(Uyeler.class);
	          Root<Uyeler> root = query.from(Uyeler.class);
	          query.select(root);
	          Query<Uyeler> q=session.createQuery(query);
	          List<Uyeler> members=q.getResultList();
	          session.close();
	          
	          for(Uyeler member:members)
	          {
	        	 
	        	  if(lblbookreturnmembertc.getText().equals(member.getUyeTc().toString()))
	        	  {
	        		 
      				session = HibernateUtil.getSessionFactory().openSession();
	        			
	        		  List<Kitaplar>booklist= new ArrayList<Kitaplar>(member.getKitaplars());
  	        		  //Iterator itera=booklist.iterator();
  	        		//while(itera.hasNext())
	        		  for(int i=0;i<booklist.size();i++)
  	        		  {
  	        			
  	        			Kitaplar k=booklist.get(i);
  	        			if(lblbookreturn1bookname.getText().equals(k.getKitapAdi()))
  	        			{
  	        				System.out.println("dfkl<");
  	        				k.setKitapAlisTarihi(null);
  	        				k.setKitapUyedeMi(false);
  	        				k.setUyeler(null);
  
  	        				session.update(k);
  	        				session.beginTransaction().commit();
  	        				member.getKitaplars().remove(k);
  	        				continue;
  	        				
  	        			}
  	        			
  	        			else if(lblbookreturn2bookname.getText().equals(k.getKitapAdi()))
  	        			{
  	        				System.out.println("<sndkjskjgkf");
  	        				k.setKitapAlisTarihi(null);
  	        				k.setKitapUyedeMi(false);
  	        				k.setUyeler(null);
                            session.update(k);
  	        				session.beginTransaction().commit();
  	        				member.getKitaplars().remove(k);
  	        				continue;
  	        				
  	        			}
  	        			else if(lblbookreturn3bookname.getText().equals(k.getKitapAdi()))
  	        			{
  	        				
  	        				System.out.println("<zckjzjcvþldzflbgþd.xdcAS");
  	        				k.setKitapAlisTarihi(null);
  	        				
  	        				k.setKitapUyedeMi(false);
  	        				k.setUyeler(null);
                            session.update(k);
  	        				session.beginTransaction().commit();
  	        				member.getKitaplars().remove(k);
  	        				continue;
  	        				
  	        			}
  	        		  }
	        	
	        		  session.close();
	        		  JOptionPane.showMessageDialog(null, "Ýade iþlemi gerçekleþmiþtir","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);
	        		  lbllbookrmembername.setText("");
	          		lblbookrmembersurname.setText("");
	          		lblbookreturnmembername.setText("");
	          		lblbookreturn1bookid.setText("");
	          		lblbookreturn1bookname.setText("");
	          		imgvbookreturn1bookimage.setImage(null);
	          		btnbookreturn1bookdelete.setVisible(false);
	          		btnbookreturn2bookdelete.setVisible(false);
	          		imgvbookreturn2bookimage.setImage(null);
	          		lblbookreturn2bookname.setText("");
	          		lblbookreturn2bookid.setText("");
	          		lblbookreturn3bookid.setText("");
	          		lblbookreturn3bookname.setText("");
	          		imgvbookreturn3bookimage.setImage(null);
	          		btnbookreturn3bookdelete.setVisible(false);
	          		lblbookrmembertc.setText("");
	          		lblbookreturnmembertc.setText("");
	          		lblbookreturnmembersurname.setText("");
	          		btnbookreturn.setVisible(false);
	        		  
	        		  
	        	  }
	          }
    	  
      }
	
      private void MailSend(String senderadress,HashSet<Kitaplar>booklist)
      {
    	  
    	   try {
    		   String from = "bsrcrs@gmail.com";
    		   String pass = "wydylhmpihefsopj";
    		   String to = senderadress;
    		   String host = "smtp.gmail.com";
    		   Properties props = System.getProperties();
    		   props.put("mail.smtp.starttls.enable", "true");
    		   props.put("mail.smtp.host", host);
    		   props.put("mail.smtp.user", from);
    		   props.put("mail.smtp.password", pass);
    		   props.put("mail.smtp.port", "587");
    		   props.put("mail.smtp.auth", "true");
    		   javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null);
    		   MimeMessage message = new MimeMessage(session);
    		   message.setFrom(new InternetAddress(from));
    		   InternetAddress toAddress = new InternetAddress();
    		  
    		    toAddress = new InternetAddress(to);
    		   
    		  
    		    message.addRecipient(Message.RecipientType.TO, toAddress);
    		   Iterator iter=booklist.iterator();
    		   String booksname = "";
    		   
    		   while(iter.hasNext())
    		   {
    			   booksname+=((Kitaplar)iter.next()).getKitapAdi()+", ";
    		   }
    		   message.setSubject("Alýnan Kitaplar");
    		   message.setText("Merhaba"+ "\n" + "Kütüphaneden "+booksname+" kitaplarýný aldýnýz. 15 gün iade süreniz bulunmaktadýr ");
    		   Transport transport = session.getTransport("smtp");
    		   transport.connect(host, from, pass);
    		   transport.sendMessage(message, message.getAllRecipients());
    		   transport.close();
    		   System.out.println("mail gönderilmiþtir");
    		  } catch (Exception e) {
    		   e.printStackTrace();
    		  }
      }

}
