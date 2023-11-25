package huanvdph35061.fpoly.moviestime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResignterActivity extends AppCompatActivity {
    private EditText edtUser;
    private EditText edtEmail;
    private EditText edtPass;
    private EditText edtEnPass;

    private Button btnResigter;
    private FirebaseAuth rAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resignter);
        edtUser = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnResigter = findViewById(R.id.btnResigter);
        rAuth = FirebaseAuth.getInstance();

        btnResigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resigter();
            }
        });
    }
    private void Resigter(){
        String email,password;
        email=edtEmail.getText().toString();
        password=edtPass.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui Lòng Nhập Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui Lòng Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        rAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    rAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResignterActivity.this, "Dang ky thanh cong. Vui long kiem tra Email", Toast.LENGTH_SHORT).show();
                                edtUser.setText("");
                                edtEmail.setText("");
                                edtPass.setText("");
                                edtEnPass.setText("");
                                startActivity(new Intent(ResignterActivity.this,LoginActivity.class));
                            }else {
                                Toast.makeText(ResignterActivity.this, "Tao Tai khoan khong thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(ResignterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}