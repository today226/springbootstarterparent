package com.util;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class StringHelper {

    public static String implode(String[] source, String delim) {
        StringBuffer sb = new StringBuffer();

        if (source != null && source.length > 0) {
            for (String s : source) {
                if (StringUtils.isEmpty(s))
                    continue;
                sb.append(s).append(delim);
            }

            sb.delete(sb.length() - 1, sb.length());
        }

        return sb.toString();
    }

    public static String[] split(String source, String delim) {

        // NullPointException handling
        if (delim == null || delim.length() == 0) {
            return new String[]{source};
        }

        StringTokenizer tokenizer = new StringTokenizer(source.trim(), delim);
        String[] tokens = new String[tokenizer.countTokens()];
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokenizer.nextToken().trim();
        }

        return tokens;
    }

    public static boolean existsSurrogateArea(String val) {
        if (StringUtils.isEmpty(val))
            return false;

        int len = val.length();
        for (int i = 0; i < len; i++) {
            char c = val.charAt(i);
            if (0xD800 <= c && c <= 0xDBFF || 0xDC00 <= c && c <= 0xDFFF) {
                return true;
            }
        }
        return false;
    }

    public static String removeSurrogateArea(String val) {
        if (val == null)
            return null;

        StringBuffer buf = new StringBuffer();
        int len = val.length();
        for (int i = 0; i < len; i++) {
            char c = val.charAt(i);
            if (0xD800 <= c && c <= 0xDBFF || 0xDC00 <= c && c <= 0xDFFF) {
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String convertUnicode(String src) {
        char[] temp = src.toCharArray();
        StringBuffer result = new StringBuffer(temp.length);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == '&' && temp.length > i + 7 && temp[i + 1] == '#' && temp[i + 7] == ';') {
                try {
                    result.append((char) Integer.parseInt(src.substring(i + 2, i + 7)));
                    i = i + 7;
                } catch (NumberFormatException e) {
                    result.append(temp[i]);
                }
            } else
                result.append(temp[i]);
        }
        result.trimToSize();
        return result.toString();
    }

    public static String convertSpecialToNormal(String orgStr) {
        if (StringUtils.isNotEmpty(orgStr))
            return orgStr.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&apos;", "'")
                    .replaceAll("&quot;", "\"").replaceAll("&#xD;", "\n").replaceAll("&#xA;", "\n").replaceAll("&#10;", "\n")
                    .replaceAll("&#038;", "&").replaceAll("&#039;", "'").replaceAll("&#8216;", "'").replaceAll("&#8217;", "'").replaceAll("&#8211;", "-")
                    .replaceAll("&#8220;", "'").replaceAll("&#8221;", "'").replaceAll("&#187;", "-").replaceAll("&#xB7;", " ")
                    .replaceAll("&#44;", ",").replaceAll("&#38;", "&").replaceAll("&#39;", "'").replaceAll("&#8230;", "...").replaceAll("&#8203;", "");
        else
            return orgStr;
    }

    public static String convertNormalToSpecial(String orgStr) {
        // (',")는 태그 안 속성값에 있을 때만 문제가 되므로 여기에서는 제외함
        if (StringUtils.isNotEmpty(orgStr))
            return orgStr.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "&#xA;");
        else
            return orgStr;
    }

    public static boolean isHangul(String text) {
        Pattern pattern = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static String extractHangle(String text) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= '\uAC00' && c <= '\uD7AF') ||
                    (c >= '\u1100' && c <= '\u11FF')
                    || (c >= '\u3130' && c <= '\u318F')) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getThumbnail(String convertUrl, String orgImgUrl, String widthXheight) {
        return getThumbnail(convertUrl, orgImgUrl, widthXheight, false);
    }

    public static String getThumbnail(String convertUrl, String orgImgUrl, String widthXheight, boolean isFitin) {
        if (StringUtils.isNotEmpty(widthXheight)) {
            StringBuffer sb = new StringBuffer();
            String orgImg = orgImgUrl.replace("http://", "").replace("https://", "");
            sb.append(convertUrl).append("/unsafe/");
            if (isFitin)
                sb.append("fit-in/");
            sb.append(widthXheight).append("/").append(orgImg);
            return sb.toString();
        } else {
            return orgImgUrl;
        }
    }

    public static String fastReplace(String input, Map<String, String> pairs) {
        // Reverse lexic-order of keys is good enough for most cases,
        // as it puts longer words before their prefixes ("tool" before "too").
        // However, there are corner cases, which this algorithm doesn't handle
        // no matter what order of keys you choose, eg. it fails to match "edit"
        // before "bed" in "..bedit.." because "bed" appears first in the input,
        // but "edit" may be the desired longer match. Depends which you prefer.
        final Map<String, String> sorted =
                new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(pairs);
        final String[] keys = sorted.keySet().toArray(new String[sorted.size()]);
        final String[] vals = sorted.values().toArray(new String[sorted.size()]);
        final int lo = 0, hi = input.length();
        final StringBuilder result = new StringBuilder();
        int s = lo;
        for (int i = s; i < hi; i++) {
            for (int p = 0; p < keys.length; p++) {
                if (input.regionMatches(i, keys[p], 0, keys[p].length())) {
                    /* TODO: check for "edit", if this is "bed" in "..bedit.." case,
                     * i.e. look ahead for all prioritized/longer keys starting within
                     * the current match region; iff found, then ignore match ("bed")
                     * and continue search (find "edit" later), else handle match. */
                    // if (better-match-overlaps-right-ahead)
                    //   continue;
                    result.append(input, s, i).append(vals[p]);
                    i += keys[p].length();
                    s = i--;
                }
            }
        }
        if (s == lo) // no matches? no changes!
            return input;
        return result.append(input, s, hi).toString();
    }

    /**
     * 문자 유효성 확인
     */
    public static class valid {

        /**
         * 문자열 영문자, 숫자 확인
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean numeng(String argVal) {
            return Pattern.compile("^[0-9a-zA-Z]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        /**
         * 문자열 영문자, 숫자 포함하지 않는 문자열
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean notnumeng(String argVal) {
            return Pattern.compile("[^0-9a-zA-Z]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        /**
         * 문자열 영문자 확인
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean eng(String argVal) {
            return Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        /**
         * 문자열 숫자 확인
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean num(String argVal) {
            return Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        /**
         * 문자열 특수문자 확인
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean special(String argVal) {
            return Pattern.compile("^[!,@,#,$,%,^,&,*,?,_,~]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        /**
         * 문자열 숫자, 영문자, 특수문자 확인
         *
         * @param argVal 확인 할 문자열
         * @return boolean
         */
        public static boolean numengspecial(String argVal) {
            return Pattern.compile("^[0-9a-zA-Z!,@,#,$,%,^,&,*,?,_,~]+$", Pattern.CASE_INSENSITIVE).matcher(argVal).find();
        }

        public static boolean password(String password) {
            return Pattern.matches("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*?_~])[a-z0-9!@#$%^&*?_~]{8,20}$", password);
        }

        /**
         * 이메일 확인
         *
         * @param value 확인 할 문자열
         * @return boolean
         */
        public static boolean email(String value) {
            return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE).matcher(value).find();
        }

        /**
         * url 확인
         *
         * @param value 확인 할 문자열
         * @return boolean
         */
        public static boolean url(String value) {
            return Pattern.compile("(http|https)://[^\\s^\\.]+(\\.[^\\s^\\.]+)*", Pattern.CASE_INSENSITIVE).matcher(value).find();
        }
    }

    public static String maskId(String id) {

        if (StringUtils.isEmpty(id))
            return id;

        int length = id.length();
        if (length == 1)
            return makeAsterisks(1);
        else if (length == 2)
            return id.substring(0, 1) + makeAsterisks(1);
        else if (length == 3)
            return id.substring(0, 1) + makeAsterisks(2);
        else if (length >= 4)
            return id.substring(0, 3) + makeAsterisks(length - 3);

        return id;
    }

    /**
     * @param email {userId}@domain.com
     */
    public static String maskEmail(String email) {

        if (StringUtils.isEmpty(email))
            return email;

        /*
         * \b: A word boundary
         * \S: A non-whitespace character
         * +: one or more times
         */
        String regex = "\\b(\\S+)+@(\\S+.\\S+)";
        Matcher matcher = Pattern.compile(regex).matcher(email);
        if (matcher.find()) {
            String id = matcher.group(1); // 마스킹 처리할 부분인 userId
            /*
             * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고, 세글자인 경우 뒤 두글자만 마스킹, 세글자 미만인 경우 1자리만 남기고 마스킹함 (a**@nhent.com, a*@nhnent.com)
             */
            int length = id.length();
            if (length == 1)
                return "*@" + matcher.group(2);
            else if (length == 2)
                return id.substring(0, 1) + "*@" + matcher.group(2);
            else if (length >= 3)
                return id.substring(0, 2) + makeAsterisks(length - 2) + "@" + matcher.group(2);
        }
        return email;
    }

    public static String maskMobileNumber(String mobileNumber) {

        if (StringUtils.isEmpty(mobileNumber))
            return mobileNumber;

        if (mobileNumber.contains("-")) {
            String[] split = mobileNumber.split("-");
            if (split.length != 3)
                return mobileNumber;

            String group1st = split[0];
            String group2nd = split[1];
            String group3rd = split[2];

            if (group2nd.length() == 3)
                return group1st + "-" + group2nd.substring(0, 2) + "*-" + group3rd.substring(0, 2) + "**";
            else if (group2nd.length() == 4)
                return group1st + "-" + group2nd.substring(0, 2) + "**-" + group3rd.substring(0, 2) + "**";
        } else {
            // 123-456-7890
            if (mobileNumber.length() == 10)
                return mobileNumber.substring(0, 5) + "*" + mobileNumber.substring(6, 8) + "**";
                // 123-4567-8901
            else if (mobileNumber.length() == 11)
                return mobileNumber.substring(0, 5) + "**" + mobileNumber.substring(7, 9) + "**";
        }

        return mobileNumber;
    }

    private static String makeAsterisks(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= length; i++)
            stringBuilder.append('*');
        return stringBuilder.toString();
    }

    public static String stringByAddingPercentEscapesUsingEncoding(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] inputBytes = input.getBytes(encoding);
        StringBuilder stringBuilder = new StringBuilder(inputBytes.length);
        for (int i = 0; i < inputBytes.length; ++i) {
            int charByte = inputBytes[i] < 0 ? inputBytes[i] + 256 : inputBytes[i];
            if (charByte <= 0x20 || charByte >= 0x7F ||
                    (charByte == 0x22 || charByte == 0x25 || charByte == 0x3C ||
                            charByte == 0x3E || charByte == 0x20 || charByte == 0x5B ||
                            charByte == 0x5C || charByte == 0x5D || charByte == 0x5E ||
                            charByte == 0x60 || charByte == 0x7b || charByte == 0x7c ||
                            charByte == 0x7d)) {
                stringBuilder.append(String.format("%%%02X", charByte));
            } else {
                stringBuilder.append((char) charByte);
            }
        }
        return stringBuilder.toString();
    }

    public static String makeRandomString(int length) {
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        return temp.toString();
    }
    public static String byteArrayToHexString(byte[] bytes){ 
		
    	StringBuilder sb = new StringBuilder();
        for(final byte b: bytes)
            sb.append(String.format("%02x", b&0xff));
        return sb.toString();
	} 
    
    public static String getLPad(String str, int size, String strFillText) {
    	for(int i = (str.getBytes()).length; i < size; i++) {
    		str = strFillText + str;
    	}
    	return str;
    }

    public static String getRPad(String str, int size, String strFillText) {
    	for(int i = (str.getBytes()).length; i < size; i++) {
    		str += strFillText;
    	}
    	return str;
    }
    public static void encoding(String originalStr) {
		String [] charSet = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
		
		for (int i=0; i<charSet.length; i++) {
			
			 for (int j=0; j<charSet.length; j++) {
			  try {
			   log.debug("[" + charSet[i] +"," + charSet[j] +"] = " + new String(originalStr.getBytes(charSet[i]), charSet[j]));
			  } catch (UnsupportedEncodingException e) {
			   e.printStackTrace();
			  }
			 }
			}

	}
}
