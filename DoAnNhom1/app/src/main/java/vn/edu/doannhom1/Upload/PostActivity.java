package vn.edu.doannhom1.Upload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.Objects;

import vn.edu.doannhom1.Model.ItemModel;
import vn.edu.doannhom1.databinding.ActivityPostBinding;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;

    Uri imageUri;
    StorageReference storageReference;
    DatabaseReference reference;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        category = getIntent().getStringExtra("category");

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        storageReference = FirebaseStorage.getInstance().getReference().child("Products");
        reference = FirebaseDatabase.getInstance().getReference().child("Products");


        binding.itemImage.setOnClickListener(view -> {
            requestPermissions();
        });

        binding.btnUpload.setOnClickListener(view -> {
            String pName = binding.itemName.getText().toString();
            String pDescription = binding.itemDescription.getText().toString();
            int price = Integer.parseInt(binding.ietmPrice.getText().toString());
            String capacity = binding.itemCapacity.getText().toString();

            if (pName.isEmpty() || pDescription.isEmpty() || capacity.isEmpty() || price == 0){
                Toast.makeText(this, "Tất cả các trường bắt buộc", Toast.LENGTH_SHORT).show();
            }else if (price < 1){
                Toast.makeText(this, "Nhập giá hợp lệ cho sản phẩm", Toast.LENGTH_SHORT).show();
            }else {
                uploadProduct(pName,pDescription,price,capacity);
            }


        });


    }

    private void uploadProduct(String pName, String pDescription, int price, String capacity) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tải lên...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final StorageReference sRef = storageReference.child(System.currentTimeMillis()+".jpg");
        sRef.putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                sRef.getDownloadUrl().addOnSuccessListener(uri -> {
            String imageUrl = uri.toString();

            ItemModel model = new ItemModel();
            String id = reference.push().getKey();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            model.setImage(imageUrl);
            model.setItemName(pName);
            model.setItemDescription(pDescription);
            model.setItemCapacity(capacity);
            model.setPrice(price);
            //model.setRating(4);
            model.setSeller(user.getUid());
            model.setItemId(id);
            model.setCategory(category);

            assert id != null;
            reference.child(id).setValue(model).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(PostActivity.this, "Sản phẩm đã tải lên", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(PostActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }));



    }

    private void requestPermissions() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent,101);
                        }
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(PostActivity.this, "Cần quyền lưu trữ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(error -> Toast.makeText(getApplicationContext(), "Xảy ra lỗi! ", Toast.LENGTH_SHORT).show())
                .onSameThread().check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            if (resultCode == RESULT_OK){
                imageUri = data.getData();
                binding.itemImage.setImageURI(imageUri);

            }
        }else {
            Toast.makeText(this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
        }
    }
}