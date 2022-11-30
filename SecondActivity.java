package fedulova.polina303.neskolkoactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fedulova.polina303.neskolkoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding = null; //инициализируем объект привязки C Sharp в файле build.gradleModule

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());//создание объекта привязки
        //setContentView(R.layout.activity_main); было
        setContentView(binding.getRoot()); //стало

        //ПОЛУЧЕНИЕ
        String formMain = getIntent().getStringExtra("message");//берем значение из объекта взаимодействия при передаче со 1й формы на 2ю
        binding.textViewTextSec.setText(formMain); //подставление сообщения во 2ю форму

        Boolean switch1 = getIntent().getBooleanExtra("switch1", false);//из объекта взаимодействия взяли состояние switchFirst с первой формы
        binding.checkBoxFirst.setChecked(switch1); //подставили состояние переменной switch1 в checkBoxFirst
        Boolean switch2 = getIntent().getBooleanExtra("switch2", false);
        binding.checkBoxSecond.setChecked(switch2);


        metodOtslejivaniaNajatia2();//метод в код добавляем слушатели на кнопки
    }

    private void metodOtslejivaniaNajatia2() {
            binding.buttonExitAppSec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog dialog = createDialog();
                    dialog.show();
                }
            });

            binding.buttonOpenDialogSec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ОТПРАВКА
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);//создание объекта взаимодействия для перехода на MainActivity
                    i.putExtra("message", binding.editTextMessageSec.getText().toString());//получаем текст из поля и передаем в параметры во вторую Activity

                    i.putExtra("check1", binding.checkBoxFirst.isChecked()); //передача состояния checkBoxFirst
                    i.putExtra("check2", binding.checkBoxSecond.isChecked()); //передача состояния checkBoxSecond

                    startActivity(i);//запуск MainActivity
                }
            });
        }

        //Метод для вывода DialogWindow для выхода из приложения
        @NonNull
        public AlertDialog createDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.custom_exit_dialog, null);//использование кастомного view
            builder.setView(dialogView)//поставили view в диалог
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем диалоговое окно
                            finish();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем диалоговое окно
                            dialog.cancel();
                        }
                    });

            return builder.create();
        }
}