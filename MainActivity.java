package fedulova.polina303.neskolkoactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import fedulova.polina303.neskolkoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null; //инициализируем объект привязки C Sharp в файле build.gradleModule

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());//создание объекта привязки
        //setContentView(R.layout.activity_main); было
        setContentView(binding.getRoot()); //стало

        //ПОЛУЧЕНИЕ
        String fromSecond = getIntent().getStringExtra("message");//берем значение из объекта взаимодействия при передаче со 2й формы на 1ю
        if (fromSecond != null) /*если значение есть*/{
            binding.textViewText.setText(fromSecond); //подставление сообщения в 1ю форму
        }

        Boolean check1 = getIntent().getBooleanExtra("check1", false); //из объекта взаимодействия взяли состояние checkBoxFirst с формы 2
        binding.switchFirst.setChecked(check1); //подставили состояние переменной switch1 в checkBoxFirst
        Boolean check2 = getIntent().getBooleanExtra("check2", false);
        binding.switchSecond.setChecked(check2);


        metodOtslejivaniaNajatia();//метод в код добавляем слушатели на кнопки
    }

    private void metodOtslejivaniaNajatia() {
        binding.buttonExitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = createExitDialog();
                dialog.show();
            }
        });

        binding.buttonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);//создание объекта взаимодействия для перехода на SecondActivity
                //ОТПРАВКА
                i.putExtra("message", binding.editTextMessage.getText().toString());//получаем текст из поля и передаем в параметры во вторую Activity

                i.putExtra("switch1", binding.switchFirst.isChecked()); //передача состояния switchFirst
                i.putExtra("switch2", binding.switchSecond.isChecked()); //передача состояния switchSecond


                startActivity(i);//запуск SecondActivity
            }
        });

        binding.buttonCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = createMessageDialog();
                dialog.show();
            }
        });
    }

    //Метод для вывода DialogWindow для выхода из приложения
    @NonNull
    public AlertDialog createExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.custom_exit_dialog, null);//использование кастомного view
        builder.setView(dialogView)//поставили view в диалог
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); //выход из приложения
                        dialog.cancel();// Закрываем диалоговое окно
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

    //Метод для вывода DialogWindow для customDialog
    @NonNull
    public AlertDialog createMessageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.custom_message_dialog, null);//использование кастомного view

        builder.setView(dialogView)//поставили view в диалог
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText messageCustom = dialogView.findViewById(R.id.editText_messageCustom); //нахождение editText_messageCustom по id
                        binding.textViewText.setText(messageCustom.getText().toString()); //взяли текст из editText_messageCustom
                        dialog.cancel();// Закрываем диалоговое окно
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });

        return builder.create();
    }


}