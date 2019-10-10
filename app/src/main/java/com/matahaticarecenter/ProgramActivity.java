package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.matahaticarecenter.adapter.ProgramAdapter;
import com.matahaticarecenter.model.ProgramModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity {

    private void loadData(List<ProgramModel> programModels) {
        programModels.add(new ProgramModel(R.drawable.program_img, "In House Training", "Program ini adalah merupakan pola pendekatan dengan motivasi untuk membuka gembok-gembok potensi manusia yang masih terbelenggu oleh pemikiran yang kontra produktif, serta menjadi inspirasi agar etos kerja pada masing-masing individu berorientasi pada tindakan dan bermotivasi tinggi dalam mengambil untuk mengejar tujuannya", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Outbound Training", "Untuk mendorong pengelolaan organisasi atau lembaga agar lebih profesional dan meningkatkan performance kerja sama tim, maka kami membuat program yang mengimplementasikan tantangan yang menyenangkan sebagai sarana untuk mengasah kompetensi diri sehingga menjadi SDM yang selalu siap menghadapi tantangan masa depan.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Gathering", "Program yang ditujukan bagi keluarga maupun perusahaan yang membutuhkan refreshing karena banyaknya rutinitas kerja yang menghabiskan energi. Untuk itu kami membuat program yang inovatif sehingga dapat mencairkan kejenuhan sehingga menimbulkan semangat baru dalam bekerja nantinya.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Event and Kid Science Outbound", "Dengan memadukan antara education, environment dan entertainment kami membuat program bagi anak-anak yang bisa mengakomodir aktivitas yang menyenangkan sekaligus menantang bagi mereka. Dengan permainan-permainan yang kita desain sesuai dengan tingkat pertumbuhannya agar kapasitas kreatif dapat kita tumbuhkan dengan membangun keberanian dan inovasinya.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Event Organizer", "Pengelolaan sebuah program formal maupun non formal tentunya membutuhkan penanganan yang maksimal sehingga bisa menghasilkan acara yang menarik dan selalu dikenang. Namun terkadang banyak dari kita yang tidak bisa menanganinya sendiri karena kesibukan utama masing-masing. Untuk itu kami juga siap memberikan solusi yang tepat bagi kebutuhan organisasi, instansi mapun lembaga lainnya dalam penyelenggaraan event untuk pendidikan karakter.", "", ""));
    }

    private void loadData2(List<ProgramModel> programModels) {
        programModels.add(new ProgramModel(R.drawable.program_img, "Traning of Trainers (ToT)", "Materi yang diberikan di program ToT merupakan materi dasar arti hakekat kesukarelawanan di matahati care centre, dimana team akan dididik untuk memiliki mental dan fisik yang siap untuk mengamalkan program pendidikan karakter kapan saja dan di mana saja mereka berada. Program ini diharapkan mampu mencetak tenaga professional dan team terbaik untuk menjadi detektif karakter, yang harus mampu memberikan pengabdian dan performa terbaik yang dimilikinya.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Smart Parenting", "Program ini ditujukan untuk para orangtua dan guru, dengan memberikan materi yang berkaitan dengan pemahaman tentang dunia anak dan orangtua. Harapannya, orang tua dapat lebih memahami perkembangan dunia anak sesuai fase mereka sehingga dapat membangun komunikasi yang sehat antara orangtua dan anak dalam membimbing mereka.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Positive Character Camp", "Program positive character camp  merupakan program pembentukan karakter positif sejak usia dini, dimana para peserta didik diberikan wacana pendidikan dengan menguatkan performa  dan apresiaisi potensi positif dalam diri peserta didik dengan konsep memberikan simulasi kegiatan proyek kebaikan yang sebenarnya, diharapkan peserta didik mampu menguatkan dirinya bagaimana cara menjadi pribadi yang baik dan beradab.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Workshop Seminar", "Program seminar akan selalu menghadirkan tema yang berkaitan dengan dunia karakter. Materi dapat disesuaikan dengan tema pembahasan, tujuan serta katergori peserta. Program seminar akan diisi oleh pembicara yang ahli di bidangnya masing-masing. Adapun tim ahli ini terdiri dari praktisi dan pendidik di bidang pendidikan karakter, psikolog serta para praktisi dan tenaga ahli dalam berbagai bidang aplikatif misalnya perkebunan, pertanian, keuangan dan sebagainya.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Travel Edukasi", "Sebuah kegiatan liburan akhir tahun bagi anak-anak Indonesia, yang menyediakan  serangkaian kegiatan dengan model  kelas-kelas professional yang langsung dibimbing oleh para trainer yang berpengalaman dibidangnya. Travel edukasi Liburan Ceria menyediakan kesempatan bagi anak- anak untuk memperkuat keberanian dalam berbagai macam petualangan, membuat dan bersosialisasi dengan teman- teman baru, menjelajahi dunia edkasi di sekitar mereka, belajar life skill, fun learning, mengeksplorasi alam, dan mengembangkan jati diri positif.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Deteksi Dini Potensi Anak dengan 9 kecerdasan", "Program deteksi dini potensi anak dengan 9 kecerdasan merupakan program mendeteksi potensi anak sejak dini dengan dilakukan tes tulis dan interview dengan 9  pos kecerdasan dan pos deteksi motorik halus,dengan program ini akan diketahui gaya belajar dan potensi anak sesuai dengan deteksi yang dilakukan. Pihak matahati care centre akan memberikan sosialisasi ke pihak sekolah dan orang tua secara langsung dalam forum parenting dan memberikan raport hasil deteksi.", "", ""));
        programModels.add(new ProgramModel(R.drawable.program_img, "Pendampingan karakter untuk keluarga ( Smart Nanny )", "Program ini merupakan serangkaian program atau treatment untuk mendampingi para orangtua dalam membimbing anak-anak, terutama mereka yang mengalami kesulitan dalam pola asuh. Materi program akan disesuaikan dengan kasus yang sedang dialami dan pemberian materi akan diberikan bukan hanya kepada anak, tapi sekaligus juga orangtua mereka. Proses pendampingan dilakukan setelah adanya MoU dengan pihak matahati dan komitment khusus untuk siap menjalankan program dalam keluarga.", "", ""));
    }

    private RecyclerView recyclerView;
    private ProgramAdapter programAdapter;
    private List<ProgramModel> programModels = new ArrayList<>();
    private Context context = ProgramActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Toolbar myToolbar = findViewById(R.id.program_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String program_type = getIntent().getStringExtra("KEY");
        if (program_type.equals("PROGRAM1")) {
            loadData(programModels);
        } else {
            loadData2(programModels);
        }

        programAdapter = new ProgramAdapter(programModels, context);
        recyclerView = findViewById(R.id.program_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(programAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
