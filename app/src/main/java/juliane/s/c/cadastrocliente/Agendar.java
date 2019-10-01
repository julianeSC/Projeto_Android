package juliane.s.c.cadastrocliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Agendar extends AppCompatActivity {
    private EditText etNome;
    private EditText etNumero;
    private EditText etData;
    private EditText etHorario;
    private Spinner spEscolha;
    private Button btnAgendar;

    //arrayList + ArrayAdapter

    private ArrayList<String> escolha;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        //ref...
        etNome = (EditText) findViewById(R.id.et_nome);
        etNumero = (EditText) findViewById(R.id.et_numero);
        etData = (EditText) findViewById(R.id.et_data);
        etHorario = (EditText) findViewById(R.id.et_horario);
        spEscolha = (Spinner) findViewById(R.id.sp_escolha);
        btnAgendar = (Button) findViewById(R.id.btn_agendar);
        //aqui firebase
        FirebaseApp.initializeApp(Agendar.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("cliente");



        //preenchendo a spinner
        escolha = new ArrayList<>();
        escolha.add(R.string.escolha_spinner);
        escolha.add(R.string.spinner_penteados);
        escolha.add(R.string.spinner_escova);
        escolha.add(R.string.spinner_coloracao);
        escolha.add(R.string.spinner_exoplastia);
        escolha.add(R.string.spinner_tratamento);
        escolha.add(R.string.spinner_interlazer);
        //instanciar o arrayAdapter
        adapter = new ArrayAdapter<String>(Agendar.this,android.R.layout.simple_dropdown_item_1line,escolha);
        //setando adapter dentro da spinner
        spEscolha.setAdapter(adapter);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente c = new Cliente();
                c.setEscolha(spEscolha.getSelectedItem().toString());

            }
        });
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente c = new Cliente();
                c.setNome(etNome.getText().toString());
                c.setTelefone(Integer.parseInt(etNumero.getText().toString()));
                c.setData(Integer.parseInt(etData.getText().toString()));
                c.setHorario(Integer.parseInt(etHorario.getText().toString()));
                c.setEscolha(spEscolha.getSelectedItem().toString());

                //aqui vem o firebase
                banco.push().setValue(c);
                Toast.makeText(getBaseContext(),"Horário marcado com sucesso",Toast.LENGTH_LONG).show();
                finish();
            }
        });//fecha onclick


    }//fecha oncreate
}//fecha classe
