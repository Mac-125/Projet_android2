package com.example.projet_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ContactActivity extends AppCompatActivity {
    String ms = "";
    LinearLayout layNaviguer , layRecherche ;
    EditText _txtId, _txtNom, _txtAdresse, _txtTel1, _txtTel2, _txtEntreprise, _txtRechercheContact;
    ImageButton _btnRecherche;
    Button _btnFirst, _btnPrevious, _btnNext, _btnLast;
    Button _btnAdd, _btnUpdate, _btnDelete;
    Button _btnCancel, _btnSave, _btnCall;
    int op = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);
        layRecherche = (LinearLayout) findViewById(R.id.layRecherche);


        _txtRechercheContact = (EditText) findViewById(R.id.txtRechercheContact);
        _txtId = (EditText) findViewById(R.id.txtId);
        _txtNom= (EditText) findViewById(R.id.txtNom);
        _txtAdresse = (EditText) findViewById(R.id.txtAdresse);
        _txtTel1 = (EditText) findViewById(R.id.txtTel1);
        _txtTel2= (EditText) findViewById(R.id.txtTel2);
        _txtEntreprise= (EditText) findViewById(R.id.txtEntreprise);

        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);

        _btnFirst = (Button) findViewById(R.id.btnFirst);
        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnNext= (Button) findViewById(R.id.btnNext);
        _btnLast = (Button) findViewById(R.id.btnLast);

        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnCall = (Button) findViewById(R.id.btnCall);

        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
         layNaviguer.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);


       _btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               op=1;
               _txtNom.setText("");
               _txtAdresse.setText("");
               _txtTel1.setText("");
               _txtTel2.setText("");
               _txtEntreprise.setText("");

               _btnSave.setVisibility(View.VISIBLE);
               _btnCancel.setVisibility(View.VISIBLE);
               _btnUpdate.setVisibility(View.INVISIBLE);
               _btnDelete.setVisibility(View.INVISIBLE);
               _btnAdd.setEnabled(false);
               layNaviguer.setVisibility(View.INVISIBLE);
               layRecherche.setVisibility(View.INVISIBLE);

           }
       });

       _btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   AlertDialog dial = Mes_Options();
                   dial.show();
               } catch (Exception e){
                   Toast.makeText(getApplicationContext(),"Sélectionner",Toast.LENGTH_SHORT).show();
               }

           }
       });
       _btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String ID = _txtId.getText().toString();
               String Name = _txtNom.getText().toString();
               String Address = _txtAdresse.getText().toString();
               String Phone1 = _txtTel1.getText().toString();
               String Phone2 = _txtTel2.getText().toString();
               String Company = _txtEntreprise.getText().toString();
               if (op == 1){
                   Ajouter_Contact ac = new Ajouter_Contact(ContactActivity.this);
                   ac.execute(ID,Name,Address,Phone1,Phone2,Company);
               } else if (op == 2) {

               }

               _btnSave.setVisibility(View.INVISIBLE);
               _btnCancel.setVisibility(View.INVISIBLE);
               _btnUpdate.setVisibility(View.VISIBLE);
               _btnDelete.setVisibility(View.VISIBLE);

               _btnSave.setVisibility(View.VISIBLE);
               _btnSave.setEnabled(true);
               _btnUpdate.setEnabled(true);
               _btnRecherche.performClick();
               //layRecherche.setVisibility(View.VISIBLE);
           }
       });

       _btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               op = 0;

               _btnSave.setVisibility(View.INVISIBLE);
               _btnCancel.setVisibility(View.INVISIBLE);


               _btnSave.setVisibility(View.VISIBLE);
               _btnSave.setEnabled(true);

               layRecherche.setVisibility(View.VISIBLE);

           }
       });




    }

    private  class Ajouter_Contact extends AsyncTask<String, Void, String> {

        AlertDialog dialog;
        Context context;

        public Ajouter_Contact(Context context) {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("Etat de connexion");
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            String Id = strings[0];
            String Nom = strings[1];
            String Adresse = strings[2];
            String Tel1 = strings[3];
            String Tel2 = strings[4];
            String Entreprise = strings[5];
            String connstr = "http:// 192.168.1.18/Projet_android/Insertion_Contact.php";

            try {
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                data += "&"+ URLEncoder.encode("Nom", "UTF-8") + "=" + URLEncoder.encode(Nom, "UTF-8");
                data += "&"+ URLEncoder.encode("Adresse", "UTF-8") + "=" + URLEncoder.encode(Adresse, "UTF-8");
                data += "&"+ URLEncoder.encode("Tel1", "UTF-8") + "=" + URLEncoder.encode(Tel1, "UTF-8");
                data += "&"+ URLEncoder.encode("Tel2", "UTF-8") + "=" + URLEncoder.encode(Tel2, "UTF-8");
                data += "&"+ URLEncoder.encode("Entreprise", "UTF-8") + "=" + URLEncoder.encode(Entreprise, "UTF-8");
                writer.write(data);
                Log.v("ConctactActivity", data);
                writer.flush();
                writer.close();
                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
                String ligne = "";
                while ((ligne = reader.readLine()) != null) {
                    result += ligne;
                }
                reader.close();
                ips.close();
                http.disconnect();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.setMessage(s);
            dialog.show();
            if (s.contains("success insertion")) {
                Toast.makeText(context, "Contact ajouté avec succès.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Problème d'ajout", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void Voir_appel(){
        if (((_txtTel1.getText().length() ==0) && (_txtTel2.getText().length() == 0)) ){

            layNaviguer.setVisibility(View.INVISIBLE);

        } else  {
            layNaviguer.setVisibility(View.VISIBLE);

        }

    }

    private AlertDialog Mes_Options(){
        AlertDialog MiDia = new AlertDialog.Builder(this)
                .setTitle("confirmation")
                .setMessage("Séléctionnez le numéro que vous allez appeler")

                .setPositiveButton("Tel1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("Tel:"+_txtTel1.getText().toString().trim()));
                        startActivity(callIntent);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Tel2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("Tel:"+_txtTel2.getText().toString().trim()));
                        startActivity(callIntent);
                        dialogInterface.dismiss();
                    }

                })
                .create();
        return MiDia;
    }



}