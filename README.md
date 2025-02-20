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
