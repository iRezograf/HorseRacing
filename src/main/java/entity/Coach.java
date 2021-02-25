package entity;

public class Coach {
        private int id;
        private String name;

        public Coach(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Coach() {
        }


        @Override
        public String toString() {
            return "Coach: {" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
}
