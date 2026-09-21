package Step_semester_3.src.main.java.week7;

public class pg9 {

    interface Playable {
        String play();
        String play(int fromSecond);
        String pause();
    }

    static abstract class MediaFile {
        private static int counter = 1000;
        private final String fileId;

        MediaFile() {
            fileId = "MF-" + (++counter);
        }

        String getFileId() {
            return fileId;
        }

        public abstract String getFormatInfo();
    }

    static class AudioFile extends MediaFile
            implements Playable {

        private String title;

        AudioFile(String title) {
            this.title = title;
        }

        public String play() {
            return "Playing audio: " + title;
        }

        public String play(int fromSecond) {
            return "Playing audio: " + title
                    + " from 0:" + fromSecond;
        }

        public String pause() {
            return "Audio paused";
        }

        public String getFormatInfo() {
            return "Audio file, ID: " + getFileId();
        }
    }

    static class Podcast implements Playable {
        private String showName;
        private int episodeNumber;

        Podcast(String showName, int episodeNumber) {
            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        public String play() {
            return "Streaming episode "
                    + episodeNumber + " of " + showName;
        }

        public String play(int fromSecond) {
            return "Streaming episode "
                    + episodeNumber + " of " + showName
                    + " from 0:" + fromSecond;
        }

        public String pause() {
            return "Podcast paused";
        }
    }

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        Podcast p = new Podcast("Tech Talk", 12);

        System.out.println(p.play());

        Playable ref = a;

        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}
