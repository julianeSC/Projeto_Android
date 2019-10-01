package juliane.s.c.cadastrocliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    //Drawer
    private Drawer result = null;
    //Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.logo)
                .addProfiles(
                        //new ProfileDrawerItem().withName("Juliane").withEmail("julianes.c.c@gmail.com"))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();
        //Inicio do menu
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.home).withIdentifier(0).withIcon(R.drawable.home),
                        new SecondaryDrawerItem().withName(R.string.menu_agenda).withIdentifier(1).withIcon(R.drawable.agendar),
                        new SecondaryDrawerItem().withName(R.string.menu_visualiza).withIdentifier(2).withIcon(R.drawable.hora),
                        new SecondaryDrawerItem().withName(R.string.menu_cadastrar_preco).withIdentifier(3).withIcon(R.drawable.preco),
                        new SecondaryDrawerItem().withName(R.string.menu_promocoes).withIdentifier(4).withIcon(R.drawable.promocao),
                        new SecondaryDrawerItem().withName(R.string.menu_cadastrar_promocao).withIdentifier(5).withIcon(R.drawable.preco)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case 0:
                                Intent it = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(it);
                                break;
                            case 1:
                                Intent it2 = new Intent(MainActivity.this, Agendar.class);
                                startActivity(it2);
                                break;
                            case 2:
                                Intent it3 = new Intent(MainActivity.this, Visualizar.class);
                                startActivity(it3);
                                break;
                            case 3:
                                Intent it4 = new Intent(MainActivity.this, Orcamento.class);
                                startActivity(it4);
                                break;
                            case 4:
                                Intent it5 = new Intent(MainActivity.this, Promocao.class);
                                startActivity(it5);
                                break;
                            case 5:
                                Intent it6 = new Intent(MainActivity.this,Cadastrarpromocao.class);
                                startActivity(it6);

                        }
                        return false;
                    }
                }).build();
    }//fecha oncreate





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

}
