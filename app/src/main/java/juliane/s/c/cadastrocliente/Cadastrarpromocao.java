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

public class Cadastrarpromocao extends AppCompatActivity {
    private EditText cpetNome;
    private Spinner cpspOpcao;
    private EditText cpetValor;
    private Button cpetCadastrar;

    private ArrayList<String> promocao;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarpromocao);

        //ref...
        cpetNome = (EditText) findViewById(R.id.cp_et_nome);
        cpspOpcao = (Spinner) findViewById(R.id.cp_sp_opcao);
        cpetValor = (EditText) findViewById(R.id.cp_et_valor);
        cpetCadastrar = (Button) findViewById(R.id.cp_btn_cadastrar);

        //aqui firebase
        FirebaseApp.initializeApp(Cadastrarpromocao.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("cadastro de promocao");
        //preenchendo a spinner
        promocao= new ArrayList<>();
        promocao.add("Cortes de cabelo");
        promocao.add("Penteados");
        promocao.add("Escova Convencional");
        promocao.add("Coloração");
        promocao.add("Exoplastia");
        promocao.add("Tratamentos Capilares");
        promocao.add("Interlazer");
        //instanciar o arrayAdapter

        adapter= new ArrayAdapter<String>(Cadastrarpromocao.this,android.R.layout.simple_dropdown_item_1line,promocao);
        //setando adapter dentro da spinner

        cpspOpcao.setAdapter(adapter);

        cpetCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PromocaoCadastro pc = new PromocaoCadastro();
                pc.setServico (cpspOpcao.getSelectedItem().toString());
            }
        });

        cpetCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PromocaoCadastro pc = new PromocaoCadastro();
                pc.setNome(cpetNome.getText().toString());
                pc.setServico(cpspOpcao.getSelectedItem().toString());
                pc.setValor(Double.parseDouble(cpetValor.getText().toString()));
                banco.push().setValue(pc);
                Toast.makeText(getBaseContext(),"Promoção cadastrada com sucesso",Toast.LENGTH_LONG).show();
                finish();
            }
        });



    }//fecha oncreate
}//fecha classe


