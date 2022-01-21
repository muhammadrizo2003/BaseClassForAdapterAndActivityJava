package com.example.project11.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project11.models.Member;
import com.example.project11.network.BaseServer;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements BaseServer {


    protected String getServer() {
        if (IS_TESTER) {
            return SERVER_DEPLOYMENT;
        } else {
            return SERVER_DEVELOPMENT;
        }
    }

    protected ArrayList<Member> prepareMemberList() {
        ArrayList<Member> members = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            members.add(new Member("Muhammadrizo" + i, "Nurullaxo'jayev" + i));
        }
        return members;
    }

    protected static boolean isEmpty(final String text) {
        return text == null || text.trim().isEmpty();
    }

}


