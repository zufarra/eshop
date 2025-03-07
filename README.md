E-Shop (Advanced Programming A)
###### Zufar Romli Amri - A - 2306202694
#Tutorial Modul 1
<details>
<summary><b>Reflection 1</b></summary>

You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Jawab:

**a. Clean Code Principles**
- Meaningful Names: Penamaan variabel, function, classes, arguments yang terdapat pada source code e-shop jelas dan deskriptif.
- Functions: Functions pada source code digunakan untuk mengulangi pengulangan kode yang berulang-ulang. Function yang digunakan pada source code hanya memiliki satu tujuan atau satu tugas. Functions juga terorganisasi dengan baik serta telah menggunakan penamaan yang deskriptif. Function juga tidak memiliki kode yang terlalu panjang.
- Comments: Pada source code e-shop, tidak terdapat comment yang bertujuan untuk menjelaskan maksud dari suatu kode karena kode yang dituliskan sudah deskriptif. Namun, comment dapat ditambahkan pada source code apabila terdapat logika yang kompleks atau pun memerlukan dokumentasi yang lebih lanjut.
- Objects and Data Structures: Variabel pada source code dibuat private agar detail implementasi tidak bocor ke luar kelas. Hal tersebut sesuai dengan prinsip Encapsulation & Information Hiding. Selain itu, source code menyediakan objek sebagai wujud dari perilaku dan menyediakan struktur data yang relevan untuk menyimpan data mentah. Namun, penggunaan getter/setter yang berlebihan juga bertentangan dengan prinsip clean code. Alih-alih hanya menampilkan data, lebih baik membuat metode yang memberikan abstraksi terhadap data tersebut.
- Error Handling: Penanganan handling pada source code belum cukup sesuai dengan clean code principles. Kode masih mengembalikan Null apabila terjadi kesalahan. Hal tersebut dapat diperbaiki dengan menambahkan try-catch-finally statements, tambahkan validasi, dan juga menggunakan exception.

**b. Secure Coding**

- Manajemen dependensi: Menggunakan pustaka resmi dan versi terbaru untuk keamanan yang lebih baik.
- Membatasi akses API: HTTP Method yang digunakan sudah benar (GET, POST) sesuai dengan operasinya. 
- Input data validation: Input data dari user sudah divalidasi dengan baik pada source code. Namun, apabila input tidak sesuai, feedback yang diberikan ke pengguna tidak ditampilkan dengan baik.
- Testing: E-shop sudah menerapkan unit test dan functional test untuk mengidentifikasi masalah dan mencegah masalah yang terjadi. Jenis testing yang digunakan adalah JUnit untuk unit testing dan Spring Boot Test untuk integrasi testing.
</details>

<details>
<summary><b>Reflection 2</b></summary>

1a. After writing the unit test, how do you feel?  

Jawab: 
Setelah menuliskan unit test dengan berbagai scenario yang ada, saya merasa lebih percaya diri dengan kode yang telah saya buat. Saya percaya kode yang telah dibuat dapat bekerja sesuai dengan yang diharapkan.

1b. How to make sure that our unit tests are enough to verify our program?

Jawab:
Tidak ada angka yang pasti untuk jumlah unit test dalam satu class, tetapi kita dapat melihat beberapa prinsip sebagai acuan:
- Minimal mencakup semua scenario utama.
- Menguji edge case dan negative case.
- Memastikan test bersifat atomic, yaitu setiap unit test hanya untuk menguji satu fitur tanpa tergantung pada test lain.

1c. How many unit tests should be made in a class?

Jawab:
Untuk memastikan unit test yang kita buat cukup dalam memverifikasi program, kita bisa melakukan beberapa hal, seperti:
- Code review, yaitu memastikan semua scenario sudah diuji.
- Code Coverage Analysis, yaitu menganalisis code coverage dengan tools yang disediakan.
- Mutation Testing, yaitu menggunakan suatu tools untuk mengubah kode dan melihat apakah ada unit test yang gagal.

1d. It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Jawab:
Jika code coverage 100%, tidak selalu berarti kode kita tidak terdapat bugs atau error. Terdapat beberapa masalah yang dapat terjadi meskipun code coverage 100%, seperti:
- Test hanya memanggil method, tetapi tidak melakukan assertion yang cukup.
- Test tidak meng-cover semua scenario edge case.
- Masalah concurrency tidak akan terlihat dengan code coverage.
Jadi, code coverage penting, tetapi harus digunakan bersamaan dengan strategi pengujian lain untuk dapat memastikan kode bebas dari bug.

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Jawab:
Jika kita menambahkan functional test baru untuk memverifikasi jumlah item dalam daftar produk dengan struktur yang sama seperti CreateProductFunctionaltTest.java, ada beberapa aspek clean code yang perlu dipertimbangkan seperti berikut:

a. Code Duplication

Jika kita membuat kelas uji baru dengan setup dan variabel yang sama (serverPort, testBaseUrl, baseUrl), maka akan terjadi duplikasi kode. Duplikasi ini membuat kode sulit dipelihara karena jika ada perubahan pada konfigurasi, kita harus memperbarui di banyak tempat.

b. Lack of Reusability

Saat ini, pengujian fungsional masih berdiri sendiri dalam satu file tanpa pemanfaatan metode atau kelas bersama. Misalnya, banyak pengujian memerlukan langkah-langkah yang sama seperti membuka halaman, mengisi formulir, dan mengklik tombol. Jika kita mengulangi langkah-langkah ini di berbagai pengujian, kode menjadi lebih sulit dibaca dan dikelola.

c. Keterbatasan dalam Maintainability

Jika kita membuat banyak file uji dengan logika yang mirip tanpa mekanisme shared utility, maka debugging atau perubahan struktur akan lebih sulit dilakukan. Jika ada perubahan pada struktur HTML di ProductList.html, seperti perubahan id elemen atau URL endpoint, maka kita harus memperbarui setiap file pengujian secara manual.

Untuk meningkatkan kebersihan kode, kita dapat menerapkan beberapa hal:

a. Membuat kelas utility untuk setup dan common actions

Kita bisa membuat kelas helper untuk mengelola tindakan berulang seperti membuka halaman, mencari elemen, dan mengisi formulir.

b. Menggunakan Parameterized Test untuk mengurangi duplikasi

Jika ada pengujian yang mirip tetapi menggunakan data berbeda, kita bisa memakai @ParameterizedTest dari JUnit.

c. Menggunakan Page Object Model (POM)

Alih-alih menuliskan selector By.id(...) langsung dalam setiap metode uji, kita bisa membuat kelas Page Object untuk memisahkan UI dan logic testing.

d. Membuat Superclass untuk mengelola setup

Jika semua functional test membutuhkan setup yang sama, lebih baik kita buat superclass saja.
</details>

#Tutorial Modul 2
<details>
<summary><b>Reflection</b></summary>
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
  
Jawab: 

  Berikut beberapa kode yang bermasalah secara kualitas dan perbaikannya:
  
  a. public void delete(String productId); ==> void delete(String productId);
  
  b. public Product edit(Product product, String productId); ==> Product edit(Product product, String productId);
  
  c. public Product create(Product product); ==> Product create(Product product);
  
  d. public Product findProductById(String productId); ==> Product findProductById(String productId);
  
  e. public List<Product> findAll(); ==> List<Product> findAll();
  
Jadi, yang saya lakukan agar kualitas kode di atas membaik adalah dengan mengganti modifier dari public menjadi default dalam interface ProductService. Hal tersebut dilakukan karena berkaitan dengan beberapa hal:
    
- Membatasi Aksesibilitas (Encapsulation)
    
Dengan menjadikan metode dalam ProductService default (tanpa modifier public), kita dapat membatasi akses hanya ke kelas-kelas dalam package yang sama (id.ac.ui.cs.advprog.eshop.service). Hal ini bertujuan agar mencegah kelas-kelas lain di luar package langsung mengakses atau mengimplementasikan interface ini sehingga mengurangi risiko dependensi yang tidak diinginkan.

- Mengurangi Kontrak yang Tidak Perlu (API Design)
   
Jika interface ini hanya digunakan dalam package service, tidak perlu mengeksposnya sebagai public. Alasannya, hal tersebut dapat menyebabkan penggunaan yang tidak diinginkan oleh bagian lain dari kode. Dengan menjadikannya package-private, kita dapat memastikan bahwa ProductService hanya dapat digunakan oleh kelas-kelas dalam package service, termasuk ProductServiceImpl.

- Meminimalkan Risiko Implementasi yang Tidak Dikontrol
  
Jika ProductService bersifat public, siapa pun dapat membuat implementasi ProductService sendiri di luar kendali. Dengan menjadikannya package-private, kita dapat membatasi kemungkinan implementasi liar yang tidak sesuai dengan arsitektur aplikasi yang dirancang.

- Meningkatkan Keamanan dan Maintainability
   
Dengan membatasi akses, kita dapat mengurangi kemungkinan modifikasi yang tidak disengaja atau tidak terkontrol oleh bagian lain dari aplikasi. Ketika kita perlu mengubah ProductService, kita hanya perlu mempertimbangkan dampaknya pada kelas-kelas dalam package yang sama, bukan seluruh aplikasi.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
   
Jawab:

Berdasarkan CI/CD workflows yang telah dikonfigurasi dalam ci.yml, pmd.yml, dan scorecard.yml, implementasi yang ada telah memenuhi definisi Continuous Integration (CI). ci.yml memastikan bahwa setiap push atau pull request menjalankan unit test dengan Gradle, yang sesuai dengan prinsip CI dalam mendeteksi kesalahan lebih awal. pmd.yml memperkuat kualitas kode dengan analisis static code menggunakan PMD, membantu dalam menjaga standar kode yang baik sebelum diintegrasikan ke main branch. Selain itu, scorecard.yml menambahkan analisis keamanan rantai pasokan kode untuk memastikan keandalan proyek. Selain itu, saya sudah melakukan konfigurasi deployment menggunakan Koyeb di GitHub Actions sehingga setiap perubahan yang telah lolos pengujian dan analisis kode akan otomatis dideploy ke lingkungan produksi, memenuhi prinsip CD dengan mempercepat siklus pengiriman perangkat lunak tanpa intervensi manual. Hal ini memastikan bahwa setiap pembaruan dapat segera tersedia bagi pengguna dengan proses yang efisien dan aman.


</details>

#Tutorial Modul 3
<details>
<summary><b>Reflection</b></summary>
  
1. Explain what principles you apply to your project!
  
Jawab: 

a. Single Responsibility Principle (SRP):

Setiap kelas dalam proyek memiliki satu tanggung jawab yang spesifik.

Contoh penerapan pada project:

- Kelas model seperti Car dan Product hanya bertanggung jawab menyimpan data.
  
- Kelas repository menangani operasi CRUD (Create, Read, Update, Delete) untuk masing-masing entitas.
  
- Kelas service mengelola logika bisnis, sedangkan kelas controller menangani request HTTP dan berinteraksi dengan service.
  
b. Open-Closed Principle (OCP):

Kode dirancang agar terbuka untuk ekstensi tetapi tertutup untuk modifikasi.

Contoh penerapan pada project:

- Pada layer repository dan service, saya telah membuat struktur yang generik sehingga jika di masa depan perlu menambahkan entitas baru (misalnya Motorcycle), kita tidak perlu mengubah logika CRUD yang sudah ada; cukup membuat repository dan service baru yang mengimplementasikan abstraksi yang telah ditetapkan.
  
c. Liskov Substitution Principle (LSP):

Setiap subkelas harus dapat digunakan menggantikan kelas induknya tanpa mengubah kebenaran program.

Contoh penerapan pada project:

- Interface CarService dan ProductService diimplementasikan sedemikian rupa sehingga controller hanya bergantung pada abstraksi service, sehingga jika nanti implementasinya diganti (misalnya, dari CarServiceImpl ke implementasi lain), fungsionalitas di controller tetap berjalan dengan benar.
  
d. Interface Segregation Principle (ISP):

Interface sebaiknya dibuat kecil dan spesifik, sehingga kelas tidak dipaksa mengimplementasikan metode yang tidak diperlukan.

Contoh:

- Service dan repository didesain dengan interface yang memisahkan tanggung jawab. Dengan demikian, setiap kelas hanya mengimplementasikan fungsi-fungsi yang sesuai dengan tugasnya tanpa “terbebani” dengan metode-metode yang tidak relevan.

e. Dependency Inversion Principle (DIP):

Modul tingkat tinggi tidak bergantung pada modul tingkat rendah, melainkan pada abstraksi.

Contoh:

- Kelas controller bergantung pada interface service (misalnya, CarService dan ProductService) daripada bergantung langsung pada implementasi konkret. Hal ini membuat sistem menjadi lebih fleksibel karena jika nanti implementasi service diubah, controller tidak perlu diubah.

2. Explain the advantages of applying SOLID principles to your project with examples.

Berikut beberapa keuntungan dalam mengaplikasikan SOLID principles:

a. Kemudahan Pemeliharaan:

Dengan SRP, setiap kelas memiliki tanggung jawab yang jelas. Misalnya, jika terjadi masalah pada operasi CRUD, kita hanya perlu memeriksa kelas repository tanpa harus menyusuri logika bisnis yang terdapat di service atau controller.

b. Reusabilitas dan Ekstensibilitas:

Penerapan OCP memungkinkan penambahan entitas baru tanpa harus mengubah kode yang sudah ada. Contohnya, struktur repository yang generik memudahkan penambahan kelas baru seperti MotorcycleRepository tanpa menyalin ulang seluruh logika CRUD.

c. Pengujian yang Lebih Mudah:

Karena tiap komponen dipisahkan (misalnya, model, repository, service, controller), unit test dapat ditulis secara terpisah untuk setiap bagian. Hal ini meningkatkan kecepatan deteksi bug dan membuat refactoring lebih aman.

d. Fleksibilitas dalam Mengganti Implementasi:

Dengan menerapkan DIP, kita dapat mengganti implementasi konkret dari repository atau service tanpa harus mengubah kode di controller. Ini sangat berguna ketika ingin berpindah dari penyimpanan in-memory ke database relasional, misalnya.

e. Mengurangi Duplikasi Kode:

Dengan menggunakan abstraksi melalui interface dan kelas generik, kita menghindari penulisan kode yang sama berulang-ulang. Misalnya, jika operasi CRUD sudah di-definisikan secara generik, penambahan entitas baru cukup mengimplementasikan antarmuka tersebut tanpa menulis ulang logika dasar.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.

Berikut beberapa kerugian akibat tidak adanya pengaplikasian SOLID principles ke dalam proyek:

a. Kode Menjadi Sulit Dipelihara:

Tanpa SRP, sebuah kelas bisa menangani banyak hal sekaligus. Misalnya, jika logika penyimpanan data, logika bisnis, dan tampilan diimplementasikan dalam satu kelas, perbaikan bug atau penambahan fitur akan menjadi sangat sulit karena perubahan di satu bagian dapat berdampak pada bagian lain.

b. Duplikasi Kode yang Tinggi:

Tanpa OCP, setiap entitas baru mungkin memerlukan penulisan ulang operasi CRUD yang mirip, sehingga meningkatkan risiko inkonsistensi dan bug.

c. Ketergantungan yang Kuat (Tight Coupling):

Jika DIP tidak diterapkan, kelas controller akan bergantung langsung pada implementasi konkrit service. Hal ini membuat sistem kurang fleksibel karena setiap perubahan pada implementasi service memaksa perubahan di banyak tempat.

d. Kesulitan dalam Pengujian:

Tanpa pemisahan tugas (SRP) dan penggunaan abstraksi (DIP, ISP), unit test akan sulit dilakukan karena satu kelas mengandung logika yang kompleks dan banyak tanggung jawab, sehingga sulit untuk menentukan sumber masalah.

e. Pengembangan yang Tidak Fleksibel:

Sistem yang tidak mengikuti SOLID cenderung kaku, di mana setiap penambahan fitur baru memerlukan perubahan besar pada kode yang sudah ada, yang meningkatkan risiko terjadinya bug dan menurunkan produktivitas tim.

</details>

#Tutorial Modul 4
<details>
<summary><b>Reflection</b></summary>
Soal Nomor 1: Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Jawab:

1. Apakah pengujian memberikan kepercayaan yang cukup terhadap kebenaran kode?

Pengujian yang dilakukan berhasil mengidentifikasi beberapa validasi yang kurang, seperti penanganan paymentData yang kosong dan status order yang tidak valid. Namun, beberapa kasus seperti ini tidak langsung ditemukan sejak awal, yang berarti cakupan pengujian masih bisa ditingkatkan.

2. Apakah pengujian gagal karena alasan yang tepat sebelum implementasi?

Sebagian besar pengujian mengikuti pendekatan TDD yang benar, di mana pengujian awalnya gagal sebelum implementasi perbaikannya. Namun, ada beberapa kasus yang mengasumsikan akan terjadi exception, tetapi ternyata validasi di kode belum ada. Ini menunjukkan perlunya pemeriksaan ulang terhadap asumsi sebelum menulis pengujian.

3. Apakah pengujian meningkatkan desain kode dan kemudahan pemeliharaan?

Pengujian yang dilakukan berhasil mengungkap potensi kekurangan dalam desain kode, seperti kurangnya validasi di konstruktor Payment. Dengan adanya pengujian ini, kode dapat diperbaiki agar lebih robust. Namun, struktur pengujian masih bisa diperbaiki dengan mengorganisir data uji lebih baik agar lebih mudah dipelihara.

4. Apakah waktu yang dihabiskan untuk pengujian bermanfaat?

Meskipun waktu yang dihabiskan untuk debugging dan memperbaiki kegagalan pengujian cukup banyak, hasil akhirnya memastikan bahwa aturan bisnis yang penting (seperti tidak boleh membuat pembayaran untuk order yang gagal) benar-benar diterapkan. Namun, ada tantangan dalam menyeimbangkan kecepatan pengembangan dengan cakupan pengujian yang menyeluruh.

5. Apa yang Perlu Ditingkatkan untuk Pengujian Berikutnya?
- Perluas cakupan pengujian, terutama untuk edge cases dan skenario kegagalan.

- Tinjau ulang asumsi sebelum menulis pengujian, agar tidak mengandalkan ekspektasi yang salah.

- Susun ulang struktur pengujian agar lebih terorganisir dan mudah dipelihara.

Soal Nomor 2: You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

Jawab: 

Refleksi terhadap Kepatuhan terhadap Prinsip F.I.R.S.T.
Prinsip F.I.R.S.T. memastikan bahwa unit test yang dibuat efektif. Berikut adalah evaluasi pengujian terhadap prinsip ini:

1. Fast (Cepat) 

Pengujian berjalan cepat karena tidak bergantung pada sumber daya eksternal.

2. Independent (Independen)

Beberapa pengujian menggunakan instance orders yang sama, yang bisa menyebabkan perilaku tak terduga jika ada perubahan dalam objek tersebut. Solusinya, setiap pengujian sebaiknya menggunakan instance baru agar tidak saling bergantung.

3. Repeatable (Dapat Diulang)

Pengujian selalu menghasilkan hasil yang sama setiap kali dijalankan, karena tidak bergantung pada faktor eksternal.

4. Self-validating (Memvalidasi Diri)

Pengujian menggunakan assertion yang jelas, sehingga mudah mengetahui apakah pengujian berhasil atau gagal.

5. Timely (Tepat Waktu) 

Beberapa validasi, seperti order status yang tidak valid, tidak diuji sejak awal. Ini menunjukkan bahwa pengujian belum sepenuhnya dilakukan sebelum implementasi kode. Agar lebih sesuai dengan TDD, pengujian harus ditulis sebelum kode utama dibuat.

Apa yang Perlu Ditingkatkan untuk Pengujian Berikutnya?
- Pastikan pengujian benar-benar independen, hindari penggunaan instance bersama.
- Tuliskan pengujian lebih awal agar lebih sesuai dengan metodologi TDD.


</details>

