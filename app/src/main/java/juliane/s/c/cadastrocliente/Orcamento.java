package juliane.s.c.cadastrocliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Orcamento extends AppCompatActivity {
    private Button tobtnPrecos;
    private EditText toetNome;
    private Spinner tospServicos;
    private EditText toetPreco;
    private Button tobtnCadastrar;

    private ArrayList<String> servico;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);

        //ref....

        tobtnPrecos = (Button) findViewById(R.id.to_btn_texto);
        toetNome = (EditText) findViewById(R.id.to_et_nome_resp);
        tospServicos = (Spinner) findViewById(R.id.to_sp_servicos);
        toetPreco = (EditText) findViewById(R.id.to_et_preco);
        tobtnCadastrar = (Button) findViewById(R.id.to_btn_cadastrar);

        //aqui firebase
        FirebaseApp.initializeApp(Orcamento.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("servico");
        //preenchendo a spinner
        servico = new ArrayList<>();
        servico.add(R.string.escolha_spinner);
        servico.add(R.string.spinner_penteados);
        servico.add(R.string.spinner_escova);
        servico.add(R.string.spinner_coloracao);
        servico.add(R.string.spinner_exoplastia);
        servico.add(R.string.spinner_tratamento);
        servico.add(R.string.spinner_interlazer);
        //instanciar o arrayAdapter

       adapter= new ArrayAdapter<String>(Orcamento.this,android.R.layout.simple_dropdown_item_1line,servico);
        //setando adapter dentro da spinner
        tospServicos.setAdapter(adapter);
        tobtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Servicos s = new Servicos();
                s.setServico(tospServicos.getSelectedItem().toString());
            }
        });
        tobtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Servicos s = new Servicos();
                s.setNome(toetNome.getText().toString());
                s.setServico(tospServicos.getSelectedItem().toString());
                s.setValor(Double.parseDouble(toetPreco.getText().toString()));
                //aqui vem o firebase
                banco.push().setValue(s);
            Toast.makeText(getBaseContext(),"Preço cadastrado com sucesso",Toast.LENGTH_LONG).show();
                finish();
            }
        });






    }//fecha oncreate
}//fecha classe
