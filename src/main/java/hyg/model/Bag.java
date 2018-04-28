package hyg.model;

public class Bag {

        private float weight;
        private float size_length;
        private float size_width;
        private float size_height;
        public Bag( float weight, float size_height, float size_length, float size_width)
        {

            this.size_height=size_height;
            this.size_length=size_length;
            this.size_width=size_width;
            this.weight=weight;
        }



        public float getSize_height() {
            return size_height;
        }

        public float getSize_length() {
            return size_length;
        }

        public float getSize_width() {
            return size_width;
        }

        public float getWeight() {
            return weight;
        }

}
