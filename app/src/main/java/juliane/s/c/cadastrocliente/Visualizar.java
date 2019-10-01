package juliane.s.c.cadastrocliente;

import android.support.v7.app.AlertDialog;
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


public class Visualizar extends AppCompatActivity {
    private ListView lvVisualizar;
    private ArrayList<Cliente> cliente;
    private ArrayAdapter<Cliente> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        //ref..
        lvVisualizar = (ListView) findViewById(R.id.lv_visualizar);

        //aqui firebase
        FirebaseApp.initializeApp(Visualizar.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("cliente");

        cliente = new ArrayList<>();
        adapter = new ArrayAdapter<Cliente>(Visualizar.this,android.R.layout.simple_list_item_1,cliente);
        lvVisualizar.setAdapter(adapter);


        banco.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cliente.clear();//limpando arraylist
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    //pegando o valor e mapeando para objeto cliente
                    Cliente c = data.getValue(Cliente.class);
                    //NÃO ESQUECER!  DELETAR,NEM ALTERAR
                    c.setKey(data.getKey());
                    cliente.add(c);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvVisualizar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente c = (Cliente) lvVisualizar.getItemAtPosition(i);
               Toast.makeText(getBaseContext(),"O cliente escolhido é: "+c.toString(),Toast.LENGTH_LONG).show();



            }
        });
        lvVisualizar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente c = (Cliente) lvVisualizar.getItemAtPosition(i);
                //removendo do arrayList
                banco.child(c.getKey()).removeValue();
                cliente.remove(i);


                //notificando o adptr se o item foi removido
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),getResources().getString(R.string.notificacao_remocao),Toast.LENGTH_LONG).show();
                return true;
            }
        });






    }//fechs oncreate
}//fecha classe
