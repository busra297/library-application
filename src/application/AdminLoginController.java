package application;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.Admin;

public class AdminLoginController implements Initializable {

	@FXML
	private TextField txtadminname;
	
	@FXML
	private TextField txtadminsurname;
	
	@FXML
	private TextField txtadminpassword;
	
	@FXML
	private CheckBox  cboxrmbme;
	
	@FXML
	private Button btnadminlogin;
	
	Preferences preferences;
	
	int sayac=0;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO AutoXML-generated method stub
		preferences=Preferences.userNodeForPackage(AdminLoginController.class);
		if(preferences.getBoolean("aktifmi",false))
		{
			cboxrmbme.setSelected(true);
			txtadminname.setText(preferences.get("name", null));
			txtadminsurname.setText(preferences.get("surname", null));
			txtadminpassword.setText(preferences.get("password", null));
		
			
		}
	}
	@FXML
	public void AdminLogin()
	{
		if(cboxrmbme.isSelected()==true)
		{
			preferences.putBoolean("aktifmi",true);
			preferences.put("name",txtadminname.getText());
	    	preferences.put("surname",txtadminsurname.getText());
	    	preferences.put("password",txtadminpassword.getText());
		}
		else
		{
			preferences.put("name","");
	    	preferences.put("surname","");
	    	preferences.put("password","");
	    	preferences.putBoolean("aktifmi",false);
		}
	
		
		    Session session = HibernateUtil.getSessionFactory().openSession();
		     session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
	        Root<Admin> root = query.from(Admin.class);
	        query.select(root);
	        Query<Admin> q=session.createQuery(query);
	        List<Admin> admins=q.getResultList();
	        session.close();
	        for (Admin admin : admins) {
	        if(txtadminname.getText().equals(admin.getAdminAdi())&& txtadminsurname.getText().equals(admin.getAdminSoyadi())&&txtadminpassword.getText().equals(admin.getAdminSifre()))
	        {
	        	
	        	try {
	        		Stage primaryStage=new Stage();
	    			AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
	    			Scene scene = new Scene(anchorpane,900,800);
	    			 Stage curentstage=(Stage) btnadminlogin.getScene().getWindow();
	    			 curentstage.hide();
	    			primaryStage.setScene(scene);
	    			primaryStage.show();
	    			sayac=1;
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	        }
	                	
	}
            if(sayac==0)
             JOptionPane.showMessageDialog(null, "Giriþ Baþarýlý Deðil.Lütfen Doðru Bilgilerilerle Tekrar Giriniz. ","Bilgilendirme Mesajý",JOptionPane.INFORMATION_MESSAGE);

}
	
	@FXML
	private void NewCreateAccount() throws IOException
	{
		Stage primaryStage=new Stage();
		AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminAccount.fxml"));
		Scene scene = new Scene(anchorpane,500,500);
		 Stage curentstage=(Stage) btnadminlogin.getScene().getWindow();
		 curentstage.hide();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
