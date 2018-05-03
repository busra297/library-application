package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.Admin;

public class AdminAccountController implements Initializable{
	
	@FXML
	private TextField txtadminaccountname,txtadminaccountsurname,txtadminaccountpassword,txtadminaccountpaswordagain,txtadminaccounteposta,txtadminaccountadress;
	
	@FXML
	private Button btnaccount;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	private void CreateAccount()
	{
		if(txtadminaccountpassword.getText().equals(txtadminaccountpaswordagain.getText()))
		{
			Session session=HibernateUtil.getSessionFactory().openSession();
			Admin admin=new Admin();
			admin.setAdminAdi(txtadminaccountname.getText());
			admin.setAdminSoyadi(txtadminaccountsurname.getText());
			admin.setAdminSifre(txtadminaccountpassword.getText());
			admin.setAdminEposta(txtadminaccounteposta.getText());
			admin.setAdminAdres(txtadminaccountadress.getText());
			
			session.save(admin);
			session.beginTransaction().commit();
			session.close();
			
			try {
        		Stage primaryStage=new Stage();
    			AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
    			Scene scene = new Scene(anchorpane,900,800);
    			 Stage curentstage=(Stage) btnaccount.getScene().getWindow();
    			 curentstage.hide();
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
			
		}
	}
	
	@FXML
	private void AccountBack() throws IOException
	{
		Stage primaryStage=new Stage();
		AnchorPane anchorpane = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
		Scene scene = new Scene(anchorpane,500,500);
		 Stage curentstage=(Stage) btnaccount.getScene().getWindow();
		 curentstage.hide();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
