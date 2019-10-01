package juliane.s.c.cadastrocliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Promocao extends AppCompatActivity {
    private ListView lvPreco;
    private ListView lvPromocao;

    private ArrayList<PromocaoCadastro> promocao;
    private ArrayAdapter<PromocaoCadastro> adapter;
    private ArrayList<Servicos> servicos;
    private ArrayAdapter<Servicos> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao);

        //ref....
        lvPreco = (ListView) findViewById(R.id.lv_preco);
        lvPromocao = (ListView) findViewById(R.id.lv_promocao);



        FirebaseApp.initializeApp(Promocao.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("cadastro de promocao");

        FirebaseApp.initializeApp(Promocao.this);
        final FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        final DatabaseReference banco1 = db.getReference("servico");


        promocao = new ArrayList<>();
        adapter = new ArrayAdapter<PromocaoCadastro>(Promocao.this,android.R.layout.simple_list_item_1,promocao);
        lvPromocao.setAdapter(adapter);

        servicos = new ArrayList<>();
        adapter1 = new ArrayAdapter<Servicos>(Promocao.this,android.R.layout.simple_list_item_1,servicos);
        lvPreco.setAdapter(adapter1);




        banco.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                promocao.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    PromocaoCadastro pc = data.getValue(PromocaoCadastro.class);
                    pc.setKey(data.getKey());
                    promocao.add(pc);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        banco1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                servicos.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Servicos s = data.getValue(Servicos.class);
                    s.setKey(data.getKey());
                    servicos.add(s);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvPromocao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PromocaoCadastro pc = new PromocaoCadastro();
                Toast.makeText(getBaseContext(),"Promoção é: "+pc.toString(),Toast.LENGTH_LONG).show();
            }
        });
        lvPromocao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PromocaoCadastro pc = (PromocaoCadastro) lvPromocao.getItemAtPosition(i);
                banco.child(pc.getKey()).removeValue();
                promocao.remove(i);

                //notificando o adpter se o item foi removido
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),getResources().getString(R.string.notificacao_promocao),Toast.LENGTH_LONG).show();

            }

        });
        lvPreco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Servicos s = new Servicos();
                Toast.makeText(getBaseContext(),"Serviço é: "+s.toString(),Toast.LENGTH_LONG).show();
            }
        });

        lvPreco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Servicos s = (Servicos) lvPreco.getItemAtPosition(i);
                banco1.child(s.getKey()).removeValue();
                servicos.remove(i);

                //notificando o adpter se o item foi remvido
                adapter1.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),getResources().getString(R.string.noticacao_preco),Toast.LENGTH_LONG).show();

            }
        });




    }//fecha oncreate
}//fecha classe
