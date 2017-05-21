public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {

        String[] array = bowlingCode.split("\\|");

        int race = 0;

        String bucket = "";
        for (int i = 0; i < 10; i++) {
            bucket = array[i];
            //该格一次命中
            if (bucket.equals("X")) {
                if (i == 9) {
                    race += lastStrike(array);
                } else if (i == 8) {
                    race += eightStike(array[i + 1], array[array.length - 1]);
                } else {
                    race += normalStrike(array[i + 1], array[i + 2]);
                }
            }
            //该格扔了两次
            else {

                //spare
                if (bucket.charAt(1) == '/') {

                    if (i == 9) {
                        race += lastSpare(array);
                    } else {
                        race += normalSpare(array[i + 1]);
                    }

                } else {

                    race += normalBucket(bucket);

                }


            }

        }


        return race;
    }

    /**
     * 计算一格的和
     *
     * @param bucket
     * @return
     */
    private int normalBucket(String bucket) {

        //如果第二次Spare，那么的和为10
        if (bucket.charAt(1) == '/')
            return 10;

        //否则计算两次
        int num = 0;
        char c = bucket.charAt(0);
        if (c == '-') {
            num += 0;
        } else {
            num += (c - '0');
        }

        c = bucket.charAt(1);
        if (c == '-') {
            num += 0;
        } else {
            num += (c - '0');
        }
        return num;
    }

    /**
     * 非第10格为Spare
     *
     * @param next
     * @return
     */
    private int normalSpare(String next) {
        int num = 10;
        char c = next.charAt(0);
        if (c == 'X') {
            num += 10;
        } else if (c == '-') {
            num += 0;
        } else {
            num += (c - '0');
        }
        return num;
    }

    /**
     * 第10格为Spare,有额外一次机会
     *
     * @param array
     * @return
     */
    private int lastSpare(String[] array) {
        int num = 10;

        String last = array[array.length - 1];
        char c = last.charAt(0);
        if (c == 'X') {
            num += 10;
        } else if (c == '-') {
            num += 0;
        } else {
            num += (c - '0');
        }

        return num;
    }

    /**
     * 不是最后两格为Strike的情况，计算后两次机会
     *
     * @param s  后一格
     * @param s1 后两格
     * @return
     */
    private int normalStrike(String s, String s1) {
        int num = 10;
        //如果下一格只扔了一次，即X，那么还需要考虑下下格
        if (s.length() == 1) {
            num += 10;
            char c = s1.charAt(0);
            if (c == 'X') {
                num += 10;
            } else if (c == '-') {
                num += 0;
            } else {
                num += (c - '0');
            }
        }
        //下一格扔了两次
        else {
            num += normalBucket(s);
        }
        return num;
    }

    /**
     * 第八格为Stike的情况，默认加后面两个分数，但是有可能因为第10格没有spare或stike，导致
     * 额外机会为0
     *
     * @return
     */
    private int eightStike(String next1, String next2) {
        int num = 10;
        //后一格为X
        if (next1.length() == 1) {
            num += 10;
            char c = next2.charAt(0);
            if (c == 'X') {
                num += 10;
            } else if (c == '-') {
                num += 0;
            } else {
                num += (c - '0');
            }


        }
        //没有额外机会，next1扔了两次
        else {
            num += normalBucket(next1);
        }
        return num;
    }


    /**
     * 最后一格为Strike，增加两次机会，桶在第11
     *
     * @param array
     * @return
     */
    private int lastStrike(String[] array) {

        int num = 10;
        String last = array[array.length - 1];
        char[] data = last.toCharArray();

        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            //一次命中10个
            if (c == 'X') {
                num += 10;
            }
            //miss
            else if (c == '-') {
                num += 0;
            } else {
                num += (c - '0');
            }
        }


        return num;

    }
    
}
