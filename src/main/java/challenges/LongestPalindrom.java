package challenges;

import java.util.Arrays;

/*
5. Longest Palindromic Substring
Medium

Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Example 3:
Input: s = "a"
Output: "a"

Example 4:
Input: s = "ac"
Output: "a"

Test:
"azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc"
--> sooos

"anugnxshgonmqydttcvmtsoaprxnhpmpovdolbidqiyqubirkvhwppcdyeouvgedccipsvnobrccbndzjdbgxkzdbcjsjjovnhpnbkurxqfupiprpbiwqdnwaqvjbqoaqzkqgdxkfczdkznqxvupdmnyiidqpnbvgjraszbvvztpapxmomnghfaywkzlrupvjpcvascgvstqmvuveiiixjmdofdwyvhgkydrnfuojhzulhobyhtsxmcovwmamjwljioevhafdlpjpmqstguqhrhvsdvinphejfbdvrvabthpyyphyqharjvzriosrdnwmaxtgriivdqlmugtagvsoylqfwhjpmjxcysfujdvcqovxabjdbvyvembfpahvyoybdhweikcgnzrdqlzusgoobysfmlzifwjzlazuepimhbgkrfimmemhayxeqxynewcnynmgyjcwrpqnayvxoebgyjusppfpsfeonfwnbsdonucaipoafavmlrrlplnnbsaghbawooabsjndqnvruuwvllpvvhuepmqtprgktnwxmflmmbifbbsfthbeafseqrgwnwjxkkcqgbucwusjdipxuekanzwimuizqynaxrvicyzjhulqjshtsqswehnozehmbsdmacciflcgsrlyhjukpvosptmsjfteoimtewkrivdllqiotvtrubgkfcacvgqzxjmhmmqlikrtfrurltgtcreafcgisjpvasiwmhcofqkcteudgjoqqmtucnwcocsoiqtfuoazxdayricnmwcg"
--> hpyyph

"ujtofmboiyyrjzbonysurqfxylvhuzzrzqwcjxibhawifptuammlxstcjmcmfvjuphyyfflkcbwimmpehqrqcdqxglqciduhhuhbjnwaaywofljhwzuqsnhyhahtkilwggineoosnqhdluahhkkbcwbupjcuvzlbzocgmkkyhhglqsvrxsgcglfisbzbawitbjwycareuhyxnbvounqdqdaixgqtljpxpyrccagrkdxsdtvgdjlifknczaacdwxropuxelvmcffiollbuekcfkxzdzuobkrgjedueyospuiuwyppgiwhemyhdjhadcabhgtkotqyneioqzbxviebbvqavtvwgyyrjhnlceyedhfechrbhugotqxkndwxukwtnfiqmstaadlsebfopixrkbvetaoycicsdndmztyqnaehnozchrakt"
--> uhhu

"aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzz zzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa"
--> "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa"

Constraints:

    1 <= s.length <= 1000
    s consist of only digits and English letters (lower-case and/or upper-case),

 */
public class LongestPalindrom {

    public String longestPalindrome(String s) {

        char[] textAsCharArr = s.toCharArray();
        int textLength = textAsCharArr.length;
        String tmpPal = null, longestPal = null;

        for (int i = 0; i < textLength; i++) {

            if (i < textLength -1 && textAsCharArr[i] == textAsCharArr[i + 1]) {
                tmpPal = String.valueOf(new char[]{textAsCharArr[i], textAsCharArr[i + 1]});

                int k = 0, j = 0;
                while (k < (textLength - 1 - i) && j < (textLength - 1 - i)) {
                    if (i -k >=0 &&(textAsCharArr[i - k] == textAsCharArr[i + 1 + k])) {
                        tmpPal = String.valueOf(Arrays.copyOfRange(textAsCharArr, i - k, i + k + 2));
                        k += 1;
                    } else if (textAsCharArr[i] == textAsCharArr[i + 1 + j] && textAsCharArr[i] == textAsCharArr[i+1]){
                        String tmpPal2 = String.valueOf(Arrays.copyOfRange(textAsCharArr, i, i + j + 2));
                        tmpPal = tmpPal.length() > tmpPal2.length() ? tmpPal : tmpPal2;
                        j += 1;
                    } else {
                        break;
                    }
                }
            }
            if (i > 0 && i < textLength -1 && textAsCharArr[i - 1] == textAsCharArr[i + 1]) {
                tmpPal = String.valueOf(new char[]{textAsCharArr[i - 1], textAsCharArr[i], textAsCharArr[i + 1]});
                int k = 1;
                while (i - k >= 0 && i + k < textLength) {
                    if ((textAsCharArr[i - k] == textAsCharArr[i + k])) {
                        tmpPal = String.valueOf(Arrays.copyOfRange(textAsCharArr, i - k, i + k + 1));
                        k += 1;
                    } else {
                        break;
                    }
                }
            }

            longestPal = populateLongestPalindrom(tmpPal, longestPal);
        }
        return longestPal != null ? longestPal : String.valueOf(textAsCharArr[0]);
    }

    private String populateLongestPalindrom(String tmpPal, String longestPal) {
        if (longestPal == null && tmpPal != null) {
            longestPal = tmpPal;
        } else if (tmpPal != null && tmpPal.length() > longestPal.length()) {
            longestPal = tmpPal;
        }
        return longestPal;
    }


}
