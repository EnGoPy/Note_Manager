package pl.wojtasik.adrian.java.basic.note;

import pl.wojtasik.adrian.java.basic.note.controller.NoteController;
import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.DatabaseUtils;
import pl.wojtasik.adrian.java.basic.note.dao.NoteDao;
import pl.wojtasik.adrian.java.basic.note.dao.NoteFiltering;
import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;
import pl.wojtasik.adrian.java.basic.note.service.NoteService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            try {
                DatabaseUtils.createConnection();
                DatabaseUtils.prepareDatabase();
                DatabaseUtils.showTables();
            } catch (NoteException e) {
                e.printStackTrace();
            }

            NoteDao noteDao = new NoteDao();
            NoteService noteService = new NoteService(noteDao);
            NoteController noteController = new NoteController(noteService);

            for(int i=1;i<=20;i++){
                NoteModel noteModel  = new NoteModel("Title nr "+i, "That's content of "+i+" note.");
                noteController.addNote(noteModel);
            }
            List<NoteModel> noteModels = noteController.allNotes();
            for (NoteModel noteModel : noteModels){
                System.out.println(noteModel);
            }

            NoteModel noteExist = noteController.read(15L);
            System.out.println(noteExist);

            NoteModel noteNotExist = noteController.read(30L);
            System.out.println(noteNotExist);

            for(NoteModel noteModel : noteController.allNotes()){
                System.out.println(noteModel.getId()+" "+noteModel.getTitle()+" "+noteModel.getContent());
            }


            NoteFiltering noteFiltering = new NoteFiltering(3, 10);
            noteController.list(noteFiltering);

            NoteModel noteModel = new NoteModel("TYTUL", "To powinno byc nadpisane");
            noteController.update(10L, noteModel);
    //
//        List<Note> list = noteDao.list();
//        System.out.println(list);

//        NoteService noteService = new NoteService();
//        noteService.setNoteDao(noteDao);
//        noteService.list();

//            NoteController noteController = new NoteController(noteService);
//            List<Note> allNotes = noteController.allNotes();
//            System.out.println(allNotes);

            //1.  Dodać metode ktora tworzy nowa notatke. Jako parametr przyjmuje klase Note i nic nie zwraca
            // Powyzsza metode rozpropagowac na wszystkie warstwy
            // Opcjonalnie dane do notatki dodac ze Scannera
            // W klasie Main w metodzie Main wywolac na Controllerze metode tworzaca notatke
            // Na Controllerze wywolac metode allNotes i sprawdzic czy zapisala sie nowa notatka

//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please write note title.");
//        String title=sc.nextLine();
//        System.out.println("Please write note content.");
//        String content=sc.nextLine();

//        Note tempNote = new Note(title, content);
//            Note tempNote = new Note("Jakis Tytul", "Jakas zawartosc");
//
//            noteController.addNote(tempNote);
//            System.out.println(noteController.allNotes());
//            System.out.println(allNotes.size());
//            allNotes.remove(0);
//            System.out.println(allNotes);
//            System.out.println(noteController.allNotes());

        }finally {
            try {
                DatabaseUtils.closeConnection();
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }

    }




}
