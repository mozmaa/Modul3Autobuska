INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO prevoznik VALUES (1, '12312421' , 'Partizanska 15', 'Dunav Prevoz' );
INSERT INTO prevoznik VALUES (2, '13412444' , 'Laze Nancica 42' , 'SeverTrans');
INSERT INTO prevoznik VALUES (3, '45325325' , 'Janka Cmelika 15' , 'Nis Prevoz');

INSERT INTO linija VALUES (1, 1, 500, 'Vrbas' , '17:00' , 1);
INSERT INTO linija VALUES (2, 60, 700, 'Novi Sad' , '05:00' , 2);
INSERT INTO linija VALUES (3, 40, 550, 'Leskovac' , '19:00' , 3);
INSERT INTO linija VALUES (4, 30, 500, 'Zrenjanin' , '18:00' , 2);
INSERT INTO linija VALUES (5, 50, 600, 'Kisac' , '20:00' , 1);