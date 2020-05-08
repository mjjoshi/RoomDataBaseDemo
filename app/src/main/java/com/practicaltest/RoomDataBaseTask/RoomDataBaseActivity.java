package com.practicaltest.RoomDataBaseTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.practicaltest.R;

public class RoomDataBaseActivity extends AppCompatActivity {
    private AppDatabase db;
    private Item item;
    private TextView itemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").build();
        Button insert = findViewById(R.id.insert);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        itemTextView = findViewById(R.id.item);
        //here I am instatiating an item which I will later add to database
        item = new Item("originalId", "itemName", "10", "1");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // here I am inserting the item into database
                            db.databaseInterface().insertAll(item);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // here I am displaying it
                                    itemTextView.setText(item.getName());
                                }
                            });
                        } catch (SQLiteConstraintException ex) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Item already added", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here I am changing current name of our item, but ID must remain same if we want to update element in database
                item.setName("newName");
                item.setAge("20");
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // and now I am updating the element in databaseRoom
                                db.databaseInterface().updateUsers(item);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        itemTextView.setText(item.getName() + " " + item.getAge());
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // and deleting
                                db.databaseInterface().deleteUsers(item);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        itemTextView.setText("item deleted");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }
}


