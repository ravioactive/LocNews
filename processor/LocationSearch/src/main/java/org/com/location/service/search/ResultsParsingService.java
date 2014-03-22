package org.com.location.service.search;

import org.com.location.LocationSearchConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ravioactive
 * Date: 12/2/13
 * Time: 2:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResultsParsingService {

    private static Pattern linkPattern = Pattern.compile("(\\[\\[)(.*?)(\\]\\])");

    private static Pattern linkPattern2 = Pattern.compile("(\\[)(.*?)(\\])");

    private static Pattern sectionPattern = Pattern
            .compile("(?m)((=){1,6})([\\s\\d\\w]*)((=){1,6}$)");


    private static String removeExtraDataInBracket(String data) {
        String parts[] = data.split("\\(");
        return parts[0].trim();
    }

    private static String removeExtraDataAfterComma(String data) {
        String parts[] = data.split(",");
        return parts[0].trim();
    }

    public static String[] parseLinks(String text) {
        String[] linkDetails = { "", "" };
        if (text != null) {
            boolean textTagSet = false;
            boolean linkTagSet = false;
            text = parseTagFormatting(text);
            linkDetails[0] = text;
            Matcher linkMatcher = linkPattern.matcher(text);
            if (linkMatcher.find()) {
                String completeLinkText = linkMatcher.group(2);
                String linkParts[] = completeLinkText.split("\\|");
                int lastIndex = completeLinkText.lastIndexOf("|");
                int difference = (completeLinkText.length() - lastIndex);
                if (lastIndex != -1 && (difference > 1)) {
                    String linkText = completeLinkText.substring(lastIndex + 1);
                    if (!linkText.equals("")) {
                        linkDetails[0] = linkMatcher.replaceFirst(linkText);
                        textTagSet = true;
                    }
                }
                String linkText = linkParts[0];
                if (linkText.contains(":")) {
                    if (linkParts[0].indexOf("#") == -1) {
                        String linkDividedOnColumn[] = linkParts[0].split(":");
                        if (((linkDividedOnColumn[0].equalsIgnoreCase("File") || linkDividedOnColumn[0]
                                .equalsIgnoreCase("media"))) && !textTagSet) {
                            linkDetails[0] = linkMatcher.replaceFirst("");
                            textTagSet = true;
                        } else if (linkDividedOnColumn[0]
                                .equalsIgnoreCase("Wiktionary")
                                || linkDividedOnColumn[0]
                                .equalsIgnoreCase("Wikipedia")
                                || linkDividedOnColumn[0]
                                .equalsIgnoreCase("Category")
                                || linkDividedOnColumn[0].equalsIgnoreCase("")) {
                            if (!textTagSet
                                    && ((completeLinkText.indexOf("|") != -1) || (linkDividedOnColumn[0]
                                    .equalsIgnoreCase("") && linkDividedOnColumn[1]
                                    .equalsIgnoreCase("Category")))) {
                                String tempString = "";
                                for (int index = 1; index < linkDividedOnColumn.length; index++) {
                                    if (index > 1) {
                                        tempString += ":";
                                    }
                                    tempString += linkDividedOnColumn[index];
                                    tempString = removeExtraDataInBracket(tempString);
                                    tempString = removeExtraDataAfterComma(tempString);
                                }
                                linkDetails[0] = linkMatcher
                                        .replaceFirst(tempString);
                                textTagSet = true;
                            } else if (linkDividedOnColumn[0]
                                    .equalsIgnoreCase("Category")) {
                                linkDetails[0] = linkMatcher
                                        .replaceFirst(linkDividedOnColumn[1]);
                                textTagSet = true;
                            } else {
                                if (!textTagSet) {
                                    linkDetails[0] = linkMatcher
                                            .replaceFirst(linkText);
                                    textTagSet = true;
                                }
                            }
                        } else {
                            if (!textTagSet) {
                                linkDetails[0] = linkMatcher
                                        .replaceFirst(linkText);
                                textTagSet = true;
                            }
                        }
                    } else {
                        if (!textTagSet) {
                            linkDetails[0] = linkMatcher.replaceFirst(linkText);
                            textTagSet = true;
                        }
                    }
                    linkDetails[1] = "";
                    linkTagSet = true;
                } else {
                    String textToBeReplaced = "";
                    if (!textTagSet) {
                        if (linkText.indexOf("(") != -1) {
                            textToBeReplaced = removeExtraDataInBracket(linkText);
                        } else if (linkText.indexOf(",") != -1) {
                            textToBeReplaced = removeExtraDataAfterComma(linkText);
                        } else {
                            textToBeReplaced = linkText.trim();
                        }
                        linkDetails[0] = linkMatcher
                                .replaceFirst(textToBeReplaced);
                        textTagSet = true;
                    }
                    if (!linkTagSet) {
                        linkText = linkParts[0];
                        if (linkText != null && !"".equals(linkText)) {
                            String tempText = linkText.replace(" ", "_");
                            String subText = tempText.length() > 2 ? tempText
                                    .substring(1) : "";
                            linkDetails[1] = Character.toUpperCase(tempText
                                    .charAt(0)) + subText;
                            linkTagSet = true;
                        }
                    }
                }
            } else {
                Matcher matchForLink = linkPattern2.matcher(text);
                if (matchForLink.find()) {
                    String data = matchForLink.group(2);
                    String dataArray[] = data.split(" ");
                    linkDetails[0] = matchForLink.replaceFirst("");
                    int length = dataArray.length;
                    if (length > 1) {
                        linkDetails[0] = matchForLink.replaceFirst(data
                                .substring(data.indexOf(" ")));
                    }
                    linkDetails[1] = "";
                }
            }
        }
        return linkDetails;
    }

    public static String parseTagFormatting(String text) {
        if (text != null) {
            text = text.replaceAll("&lt;", "<");
            text = text.replaceAll("&gt;", ">");
            text = text.replaceAll("\\s*<[^\\>]*>", "");
            text = text.trim();
        }
        return text;
    }

    public static String parseListItem(String itemText) {
        if (itemText != null) {
            itemText = itemText.replaceAll("(?m)^#*(\\s)*", "");
            itemText = itemText.replaceAll("(?m)^\\**(\\s)*", "");
            itemText = itemText.replaceAll("(?m)^;(\\s)*", "");
            itemText = itemText.replaceAll("(?m)^:(\\s)*", "");
        }
        return itemText;
    }

    public static String parseTextFormatting(String text) {
        if (text != null) {
            text = text.replaceAll("(''')", "");
            text = text.replaceAll("('')", "");
        }
        return text;
    }

    public static String parseTemplates(String text) {
        // return text.replaceAll("(?s)(\\{\\{)(.*?)(\\}\\})", "");
        if (text != null) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            char[] c = text.toCharArray();
            for (int i = 0; i < text.length()-1; i++) {
                if (c[i] == '{' && c[i + 1] == '{') {
                    count++;
                    i++;
                    continue;
                }
                if (c[i] == '}' && c[i + 1] == '}') {
                    count--;
                    i++;
                    continue;
                }
                if (count == 0) {
                    sb.append(c, i, 1);
                }
            }
            text = sb.toString();
        }
        return text;
    }

    public static String parseSectionTitle(String titleStr) {
        if (titleStr != null) {
            Matcher matcher = sectionPattern.matcher(titleStr);
            if (matcher.find()) {
                titleStr = matcher.replaceAll(matcher.group(3).trim());
            }
        }
        return titleStr;
    }

    public static String pareseWikiNewsContent(String text) throws Exception {
        text = parseTemplates(text);
        text = text.trim();
        text = parseTagFormatting(text);
        text = text.trim();
        text = parseListItem(text);
        text = text.trim();
        text = parseTextFormatting(text);
        text = text.trim();
        text = text.replaceAll("\\$", "\\\\\\$");
        Matcher linkMatcher = linkPattern.matcher(text);
        while (linkMatcher.find()) {
            String[] linkData = parseLinks(linkMatcher.group());

			/*if (linkMatcher.group(2).indexOf("Category") != -1
					|| linkMatcher.group(2).indexOf("category") != -1) {
				wikiDoc.addCategory(linkData[0]);
			} else {
				wikiDoc.addLink(linkData[0]);
			}*/
            text = linkMatcher.replaceFirst(linkData[0].replaceAll("\\$",
                    "\\\\\\$"));
            linkMatcher = linkPattern.matcher(text);
        }
        Matcher matchForLink = linkPattern2.matcher(text);
        while (matchForLink.find()) {
            String[] linkData = parseLinks(matchForLink.group());

            //wikiDoc.addLink(linkData[0]);
            text = matchForLink.replaceFirst(linkData[0].replaceAll("\\$",
                    "\\\\\\$"));
            matchForLink = linkPattern2.matcher(text);
        }


        String[] sections = text
                .split("(?m)((=){1,6})([\\s\\d\\w]*)((=){1,6}$)");
        Matcher sectionMatcher = sectionPattern.matcher(text);
        String sectionTitle = "";
        if (!sections[0].equals("")) {
            //sectionTitle = "Default";
        } else {
            sections[0] = null;
            sectionTitle = "";
        }
        for (String sectionData : sections) {
            if (sectionData == null) {
                continue;
            }
            if (sectionTitle.equals("")) {
                if (sectionMatcher.find()) {
                    sectionTitle = parseSectionTitle(sectionMatcher.group(0));
                }
            }
            //wikiDoc.addSection(sectionTitle.trim(), sectionData.trim());
            //text+= sectionTitle+"\n"+sectionData;
            text = text.replaceFirst("(?m)((=){1,6})([\\s\\d\\w]*)((=){1,6}$)", sectionTitle);
            sectionTitle = "";
        }
        return text;
    }

    public static String generateSummary(String parsedText) {
        String summary = "";
        if(parsedText==null && !parsedText.isEmpty()) {
            return summary;
        }

        int length = parsedText.length();
        if(length > LocationSearchConstants.DEFAULT_WIKINEWS_SUMMARY_LENGTH) {
            summary = parsedText.substring(0, LocationSearchConstants.DEFAULT_WIKINEWS_SUMMARY_LENGTH);
        } else {
            summary = parsedText;
        }

        return summary;
    }
}
