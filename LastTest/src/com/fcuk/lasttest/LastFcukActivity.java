package com.fcuk.lasttest;

import java.io.*;
import java.net.*;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;

public class LastFcukActivity extends Activity {

	 public TextView textView;  //declaring the textView type
	 public EditText txtEdit;  //declaring the  EditText type
	 public static String str;  // declaring the string type

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_fcuk);
        textView = (TextView) findViewById(R.id.TextView01);
        txtEdit = (EditText) findViewById(R.id.editText1);        
    }

    private class DownloadWebPageTask extends AsyncTask<Void, Void, String> {
    	
        @Override
        protected String doInBackground(Void... Params) {
          
        	str = "Inside try";
	      try {
	    	  String SendingData;
	    	  str = "Inside try";
	         
	          String u;
	          BufferedReader in = null;
	          //Here Localhost is represented by "10.0.2.2". SO use this.
	          Socket ClientSocket = new Socket("170.224.168.64",8087);
	          DataOutputStream ClientData = new DataOutputStream(ClientSocket.getOutputStream());
	          SendingData =  txtEdit.getText().toString();
	          ClientData.writeBytes(SendingData + '\n');
	          str= "Incoming Data from Server:";
	          in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
	          try{
	        	
	        	  str= "Incoming Data from Server:";
		          while ((u=in.readLine())!=null){
		            
		        	  str = u;
		          }
	          }
	          finally{
	      
	          }
	          ClientSocket.close();

			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
	      
	    	  return str;
        }

        @Override
        protected void onPostExecute(final String result) {
        	runOnUiThread (new Runnable(){
             	 public void run() {
             		textView.setText(result);
             	 }
             });
        }
      }

      public void readWebpage(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute();

      }
}
