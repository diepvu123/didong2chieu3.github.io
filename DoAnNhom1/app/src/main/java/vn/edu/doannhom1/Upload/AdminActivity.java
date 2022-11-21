package vn.edu.doannhom1.Upload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import vn.edu.doannhom1.Activity.AccountActivity;
import vn.edu.doannhom1.Activity.MyOrdersActivity;
import vn.edu.doannhom1.Authentication.LoginActivity;
import vn.edu.doannhom1.Authentication.ReferenceActivity;
import vn.edu.doannhom1.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.fruitsLyt.setOnClickListener(view -> {
//            startActivity(new Intent(AdminActivity.this,PostActivity.class)
//                    .putExtra("category","fruits"));
//        });
        binding.iphone.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this,PostActivity.class)
                    .putExtra("category","iphone"));
        });
        binding.samsung.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this,PostActivity.class)
                    .putExtra("category","samsung"));
        });
        binding.oppo.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this,PostActivity.class)
                    .putExtra("category","oppo"));
        });
        binding.redmi.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this,PostActivity.class)
                    .putExtra("category","redmi"));
        });
        binding.levono.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this,PostActivity.class)
                    .putExtra("category","levono"));
        });

        binding.txtOrders.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, MyOrdersActivity.class);
            intent.putExtra("from","shop");
            startActivity(intent);
        });

        binding.txtSignOut.setOnClickListener(view -> {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            Intent intent1 = new Intent(AdminActivity.this, ReferenceActivity.class);
                            intent1.putExtra("type","shop");
                            startActivity(intent1);
                            finish();
                        }
                    });
        });

        binding.txtAccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, AccountActivity.class);

            startActivity(intent);
        });

    }
}