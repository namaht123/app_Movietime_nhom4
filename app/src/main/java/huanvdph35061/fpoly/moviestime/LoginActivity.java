package huanvdph35061.fpoly.moviestime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private CheckBox chkLuuMK;
    private ImageView btnGG;
    private ImageView btnFB;

    private TextView txtQuenMatKhau;

    private TextView txtDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        chkLuuMK = findViewById(R.id.chkLuuMK);
        btnFB = findViewById(R.id.btnGG);
        btnFB = findViewById(R.id.btnFB);
        txtDangKy = findViewById(R.id.txtDangKy);
        txtQuenMatKhau = findViewById(R.id.txtQuenMK);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResignterActivity.class));
            }
        });
        txtQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogQuenMatKhau();
            }
        });
    }
    private void Login(){
        String email,password;
        email=edtUser.getText().toString();
        password=edtPass.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui Lòng Nhập Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui Lòng Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Tài Khoản Hoặc Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showDialogQuenMatKhau() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_quenmatkhau, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtEmail = view.findViewById(R.id.edtEmail);
        Button btnGui = view.findViewById(R.id.btnSend);
        Button btnHuy = view.findViewById(R.id.btnCancel);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(email)){
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(LoginActivity.this, "Da gui email", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Error : -"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    edtEmail.setError("Loi");
                }
            }
        });
    }
}