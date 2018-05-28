package org.study.regexp;

import org.junit.Test;

import java.util.function.Function;
import java.util.regex.MatchResult;

import static org.junit.Assert.*;

public class ReplaceFunctionTest {

    @Test
    public void shouldReplaceStringWithFunction() {
        String pattern = "%%(.+?)%%";
        String source = "Beginning is%%test $1 Me%%Trash %%test 2 You%% again trash";
        String expected = "Beginning isTEST $1 METrash TEST 2 YOU again trash";
        Function<MatchResult, String> function = this::replacementFunction;

        String result = ReplaceFunction.replace(source, pattern, function);

        assertEquals(expected, result);
   }

    @Test
    public void shouldWrongReplaceStringWithFunction() {
        String pattern = "%%(.+?)%%";
        String source = "Beginning is%%test 1 Me%%Trash %%test 2 You%% again trash";
        String expected = "Beginning isTEST 1 METrash TEST 2 YOU again trash";
        Function<MatchResult, String> function = this::replacementFunction1;

        String result = ReplaceFunction.replace(source, pattern, function);

        assertEquals(expected, result);
   }

   private String replacementFunction(MatchResult matchResult) {
       String s = matchResult.group(1).toUpperCase();
       return s;
   }

   private String replacementFunction1(MatchResult matchResult) {
       return "##cat0##\n" +
               "<script>\n" +
               "\n" +
               "    $(document).ready(function () {\n" +
               "$('.drop-down-1 li').hover(function(){\n" +
               "    $('.drop-down-1 ul li').removeClass('drop-arrow-red');\n" +
               "\n" +
               "    $(this).addClass('drop-arrow-red');\n" +
               "\n" +
               "    var index = $(this).index();\n" +
               "\n" +
               "    var id =  $('.drop-down-2 ul ').eq(index).find('.drop-arrow-red').find('a').attr('class');\n" +
               "    $('.drop-down-2 ul').addClass('none-important');\n" +
               "    $('.drop-down-2 ul').eq(index).removeClass('none-important');\n" +
               "\n" +
               "    $('.drop-down-3-block').addClass('none-important');\n" +
               "    $('.drop-down-3 .'+id).removeClass('none-important');\n" +
               "});\n" +
               "\n" +
               "\n" +
               "$('.drop-down-2 li').hover(function(){\n" +
               "    $(this).parent().find('li').removeClass('drop-arrow-red');\n" +
               "\n" +
               "    $(this).addClass('drop-arrow-red');\n" +
               "    var id = $(this).find('a').attr('class');\n" +
               "    $('.drop-down-3-block').addClass('none-important');\n" +
               "    $('.drop-down-3 .'+id).removeClass('none-important');\n" +
               "});\n" +
               "\n" +
               "\n" +
               "$('.white-line,.sailing-drop-down').hover(function() {\n" +
               "\n" +
               "    $('.hover').css({'display':'block'});\n" +
               "},function() {\n" +
               "    $('.hover').css({'display':'none'});\n" +
               "});\n" +
               "\n" +
               "$('.drop-down-1 li.no-subct').hover(function () {\n" +
               "            $('.drop-down-3').empty()\n" +
               "            if ($(this).attr('id') !== '') {\n" +
               "\n" +
               "                ajaxArticle($(this).attr('id'));\n" +
               "            }\n" +
               "        })\n" +
               "        $('.drop-down-1 li').hover(function () {\n" +
               "            var id = $(this).attr('id');\n" +
               "            if (!$(this).hasClass('no-subct') && $(this).attr('id') == '') {\n" +
               "\n" +
               "\n" +
               "                $('.drop-down-3').empty()\n" +
               "            }\n" +
               "            $('.drop-down-2 > ul').removeClass('none-important').addClass('none-important');\n" +
               "\n" +
               "            $('.drop-down-2').find('.' + id).removeClass('none-important');\n" +
               "\n" +
               "        })\n" +
               "\n" +
               "        $(\"body\").on(\"mouseenter\", \" .drop-down-2 li \", function () {\n" +
               "\n" +
               "\n" +
               "            ajaxArticle($(this).attr('id'));\n" +
               "\n" +
               "        });\n" +
               "\n" +
               "        function ajaxArticle(id) {\n" +
               "\n" +
               "            $.ajax({\n" +
               "                url: \"##absolutePath##AJAXYachtRequest.jsp;jsessionid=##session_id##?District=\" + id + \"&action=1&level=article\",\n" +
               "\n" +
               "                success: function (data) {\n" +
               "                    $('.drop-down-3').empty().append('<div class=\"drop-down-3-block\">');\n" +
               "                    $('.drop-down-3-block').append(data);\n" +
               "\n" +
               "                },\n" +
               "                select: function (event, ui) {\n" +
               "                    return false;\n" +
               "                },\n" +
               "                minLength: 2\n" +
               "            })\n" +
               "        }\n" +
               "\n" +
               "        var folderArray = [],\n" +
               "            $form = $(\"#createMenuForm\"),\n" +
               "            url = $form.attr(\"action\");\n" +
               "\n" +
               "        $.post(url, $(\"#createMenuForm\").serialize(), function (data) {\n" +
               "            $(\"#menuItems\").html(data);\n" +
               "        });\n" +
               "    });\n" +
               "\n" +
               "</script>\n" +
               "<form class=\"change-password-form\" action=\"##absolutePath##createMenu.jsp?catId=##curcat_oid##\" method=\"post\" id=\"createMenuForm\">\n" +
               "    <div class=\"flag-redbar\" style=\"height:58.6px;\">\n" +
               "        <!--  red-navbar starts-->\n" +
               "        <section class=\"red-navbar\" style=\"width:100%\">\n" +
               "            <div class=\"hover none\">\n" +
               "\n" +
               "            </div>\n" +
               "            <div class=\"max-width\">\n" +
               "                <ul id=\"menuItems\">\n" +
               "                </ul>\n" +
               "            </div>\n" +
               "        </section>\n" +
               "        <!--  red-navbar finishs-->\n" +
               "    </div>\n" +
               "</form>";
   }
}