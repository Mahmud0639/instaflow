package com.manunimahmud.instafollow.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.adapters.FollowersAdapter;
import com.manunimahmud.instafollow.databinding.FragmentProfileBinding;
import com.manunimahmud.instafollow.models.FollowersModel;
import com.manunimahmud.instafollow.models.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private FollowersAdapter adapter;
    private List<FollowersModel> list;
    private final int PICK_IMAGE = 1;
    private final int REQ_CODE = 2;
    private Uri imageUri;
    private Bitmap imageBitmap;
    private StorageReference storageReference, mStorage;
    private DatabaseReference databaseReference;
    private UploadTask uploadTask;
    private String downloadUrl = "";
    private FirebaseAuth auth;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        databaseReference.child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    try {
                        Picasso.get().load(user.getCoverPhoto()).placeholder(R.drawable.impl1).into(binding.coverPhoto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Picasso.get().load(user.getProfilePic()).placeholder(R.drawable.impl2).into(binding.profileImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    binding.userName.setText(user.getUserName());
                    binding.profession.setText(user.getProfession());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        list = new ArrayList<>();
        list.clear();
        list.add(new FollowersModel(R.drawable.my_image2));
        list.add(new FollowersModel(R.drawable.my_image3));
        list.add(new FollowersModel(R.drawable.my_image));
        list.add(new FollowersModel(R.drawable.my_image2));
        list.add(new FollowersModel(R.drawable.my_image3));
        list.add(new FollowersModel(R.drawable.my_image));
        list.add(new FollowersModel(R.drawable.my_image2));
        list.add(new FollowersModel(R.drawable.my_image3));
        list.add(new FollowersModel(R.drawable.my_image));
        list.add(new FollowersModel(R.drawable.my_image2));
        list.add(new FollowersModel(R.drawable.my_image3));
        list.add(new FollowersModel(R.drawable.my_image));

        binding.followerRV.setHasFixedSize(true);
        binding.followerRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new FollowersAdapter(getContext(), list);
        binding.followerRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });

        return binding.getRoot();
    }

    private void selectPhoto() {
        Intent imgPick = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imgPick, REQ_CODE);
    }

    private void openGallery() {
        Intent imagePick = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imagePick, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();

            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.coverPhoto.setImageBitmap(imageBitmap);
            gotoStorage();
        } else if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();

            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.profileImage.setImageBitmap(imageBitmap);
            gotoFStorage();
        }
    }

    private void gotoFStorage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        byte[] myImage = baos.toByteArray();
        mStorage = storageReference.child("profile_images").child(myImage + "." + getFileExtension(imageUri));
       uploadTask = mStorage.putBytes(myImage);
       uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
               if (task.isSuccessful()){
                   uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                   downloadUrl = String.valueOf(uri);
                                   gotoFDatabase();
                               }
                           });
                       }
                   });
               }
           }
       });

    }
    private void gotoFDatabase(){

        databaseReference.child("Users").child(auth.getUid()).child("profilePic").setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Profile photo saved.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri uri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getActivity().getContentResolver().getType(uri));
    }

    private void gotoStorage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        byte[] image = baos.toByteArray();

        mStorage = storageReference.child("cover_photo").child(image + "." + getFileExtension(imageUri));
        uploadTask = mStorage.putBytes(image);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    gotoDatabase();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void gotoDatabase() {
        databaseReference.child("Users").child(auth.getUid()).child("coverPhoto").setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Photo saved successfully!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}