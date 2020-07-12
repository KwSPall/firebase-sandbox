package org.example;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;

public class Main {

    private Firestore firestore;

    public Main(String projectId) throws IOException {
        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        Firestore db = firestoreOptions.getService();

        this.firestore = db;
    }

    void getAll() throws Exception {
        ApiFuture<QuerySnapshot> query = firestore.collection("garbage").get();

        QuerySnapshot querySnapshot = query.get();
        System.out.println("Garbage:");
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            System.out.println("Id: " + document.getId());
            System.out.println("Text: " + document.getString("text"));
            System.out.println("---");

        }
    }

    public static void main(String[] args) throws Exception {
        String projectId = "test-project-kws";

        Main main = new Main(projectId);
        main.getAll();
    }
}
