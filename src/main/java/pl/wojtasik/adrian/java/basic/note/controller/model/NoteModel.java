package pl.wojtasik.adrian.java.basic.note.controller.model;

public class NoteModel {

    private Long id;
    private String title;
    private String content;

    public NoteModel() {
    }

    public NoteModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
