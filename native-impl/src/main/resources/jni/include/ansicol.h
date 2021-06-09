/**
 * Created at 2020/7/1 06:48.
 * 
 * @author Liangcheng Juves
 */
#ifndef _ANSICOL_H
#define _ANSICOL_H

#ifdef __cplusplus
extern "C"
{
#endif

#include <string.h>
#include "lcjc.h"
#include "platf.h"

#ifdef __PLATF_WINDOWS
#include <Windows.h>
#endif

#ifdef __PLATF_WINDOWS
/* Black text. */
#define ANSI_FG_BLACK 0
#else
/* Black text. */
#define ANSI_FG_BLACK "\033[30m"
#endif

#ifdef __PLATF_WINDOWS
/* Red text. */
#define ANSI_FG_RED FOREGROUND_RED
#else
/* Red text. */
#define ANSI_FG_RED "\033[31m"
#endif

#ifdef __PLATF_WINDOWS
/* Green text. */
#define ANSI_FG_GREEN FOREGROUND_GREEN
#else
/* Green text. */
#define ANSI_FG_GREEN "\033[32m"
#endif

#ifdef __PLATF_WINDOWS
/* Yellow text. */
#define ANSI_FG_YELLOW FOREGROUND_RED | FOREGROUND_GREEN
#else
/* Yellow text. */
#define ANSI_FG_YELLOW "\033[33m"
#endif

#ifdef __PLATF_WINDOWS
/* Blue text. */
#define ANSI_FG_BLUE FOREGROUND_BLUE
#else
/* Blue text. */
#define ANSI_FG_BLUE "\033[34m"
#endif

#ifdef __PLATF_WINDOWS
/* Fuchsia text. */
#define ANSI_FG_FUCHSIA 0xd
#else
/* Fuchsia text. */
#define ANSI_FG_FUCHSIA "\033[35m"
#endif

#ifdef __PLATF_WINDOWS
/* Cyan text. */
#define ANSI_FG_CYAN FOREGROUND_GREEN | FOREGROUND_BLUE
#else
/* Cyan text. */
#define ANSI_FG_CYAN "\033[36m"
#endif

#ifdef __PLATF_WINDOWS
/* White text. */
#define ANSI_FG_WHITE FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE
#else
/* White text. */
#define ANSI_FG_WHITE "\033[37m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is black. */
#define ANSI_BG_BLACK ANSI_FG_BLACK * 16
#else
/* The background color of the console is black. */
#define ANSI_BG_BLACK "\033[40m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is red. */
#define ANSI_BG_RED ANSI_FG_RED * 16
#else
/* The background color of the console is red. */
#define ANSI_BG_RED "\033[41m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is green. */
#define ANSI_BG_GREEN ANSI_FG_GREEN * 16
#else
/* The background color of the console is green. */
#define ANSI_BG_GREEN "\033[42m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is yellow. */
#define ANSI_BG_YELLOW ANSI_FG_YELLOW * 16
#else
/* The background color of the console is yellow. */
#define ANSI_BG_YELLOW "\033[43m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is blue. */
#define ANSI_BG_BLUE ANSI_FG_BLUE * 16
#else
/* The background color of the console is blue. */
#define ANSI_BG_BLUE "\033[44m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is fuchsia. */
#define ANSI_BG_FUCHSIA ANSI_FG_FUCHSIA * 16
#else
/* The background color of the console is fuchsia. */
#define ANSI_BG_FUCHSIA "\033[45m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is cyan. */
#define ANSI_BG_CYAN ANSI_FG_CYAN * 16
#else
/* The background color of the console is cyan. */
#define ANSI_BG_CYAN "\033[46m"
#endif

#ifdef __PLATF_WINDOWS
/* The background color of the console is white. */
#define ANSI_BG_WHITE ANSI_FG_WHITE * 16
#else
/* The background color of the console is white. */
#define ANSI_BG_WHITE "\033[47m"
#endif

#ifdef __PLATF_WINDOWS
/* The console is displayed in the default state. */
#define ANSI_NONE 0 | (FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE)
#else
/* The console is displayed in the default state. */
#define ANSI_NONE "\033[0m"
#endif

#ifdef __PLATF_WINDOWS
/* The text is highlighted. */
#define ANSI_HIGH_LIGHT FOREGROUND_INTENSITY
#else
/* The text is highlighted. */
#define ANSI_HIGH_LIGHT "\033[1m"
#endif

#ifdef __PLATF_WINDOWS
#define ANSI_UNDER_LINE COMMON_LVB_UNDERSCORE
#else
#define ANSI_UNDER_LINE "\033[4m"
#endif

#ifndef __PLATF_WINDOWS

#define ANSI_FLASHING "\033[5m"
#define ANSI_REVERSE "\033[7m"
#define ANSI_BLANKING "\033[8m"

#define ANSI_CLEAR_SCREEN "\033[2J"
#define ANSI_CLEAR_CONTENT_FROM_CURSOR_OF_EOL "\033[K"

#define ANSI_MOVE_CURSOR_UP(n) "\033[%dA"
#define ANSI_MOVE_CURSOR_DOWN(n) "\033[%dB"
#define ANSI_MOVE_CURSOR_RIGHT(n) "\033[%dC"
#define ANSI_MOVE_CURSOR_LEFT(n) "\033[%dD"
#define ANSI_MOVE_CURSOR_POSITION "\033[%d;%dH"

#define ANSI_SAVE_CURSOR_POSITION "\033[s"
#define ANSI_SAVE_RESTORE_POSITION "\033[u"

#define ANSI_HIDE_CURSOR "\033[?25l"
#define ANSI_SHOW_CURSOR "\033[?25h"
#define ANSI_NULL "\0"

#else
/* For Windows. */
void SetWinConsoleAttr(WORD wAttres);
void WinConRestore();
void WinConAttrPrint(char const *str, WORD wAttres);

void WinConBlackPrint(char const *str);
void WinConRedPrint(char const *str);
void WinConGreenPrint(char const *str);
void WinConYellowPrint(char const *str);
void WinConBluePrint(char const *str);
void WinConFuchsiaPrint(char const *str);
void WinConCyanPrint(char const *str);
void WinConWhitePrint(char const *str);

inline void SetWinConsoleAttr(WORD wAttres)
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), wAttres);
}

void WinConRestore()
{
    SetWinConsoleAttr(ANSI_NONE);
}

void WinConAttrPrint(char const *str, WORD wAttres)
{
    SetWinConsoleAttr(wAttres);
    printf("%s", str);
}

inline void WinConBlackPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_BLACK);
    WinConRestore();
}

inline void WinConRedPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_RED);
    WinConRestore();
}

inline void WinConGreenPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_GREEN);
    WinConRestore();
}

inline void WinConYellowPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_YELLOW);
    WinConRestore();
}

inline void WinConBluePrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_BLUE);
    WinConRestore();
}

inline void WinConFuchsiaPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_FUCHSIA);
    WinConRestore();
}

inline void WinConCyanPrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_CYAN);
    WinConRestore();
}

inline void WinConWhitePrint(char const *str)
{
    assert(NULL != str);
    WinConAttrPrint(str, ANSI_FG_WHITE);
    WinConRestore();
}
#endif /* __PLATF_WINDOWS */

    void acolprnt(void *bg, void *fg, char const *str);

    void blackprnt(char const *str);
    void redprnt(char const *str);
    void greenprnt(char const *str);
    void yellowprnt(char const *str);
    void blueprnt(char const *str);
    void fuchsiaprnt(char const *str);
    void cyanprnt(char const *str);
    void whiteprnt(char const *str);

    inline void acolprnt(void *bg, void *fg, char const *str)
    {
        assert(NULL != bg && NULL != fg && NULL != str);
#ifdef __PLATF_WINDOWS
        WinConAttrPrint(str, ((WORD)bg) | ((WORD)fg));
        WinConRestore();
#else
    bg = (char *)bg;
    fg = (char *)fg;
    char *toprnt = mlcstr(cslen(bg) + cslen(fg) + cslen(str));
    strcpy(toprnt, bg);
    strcat(toprnt, fg);
    strcat(toprnt, "%s");
    strcat(toprnt, ANSI_NONE);
    printf(toprnt, str);
#endif
    }

    inline void blackprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConBlackPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_BLACK, str);
#endif
    }

    inline void redprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConRedPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_RED, str);
#endif
    }

    inline void greenprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConGreenPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_GREEN, str);
#endif
    }

    inline void yellowprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConYellowPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_YELLOW, str);
#endif
    }

    inline void blueprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConBluePrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_BLUE, str);
#endif
    }

    inline void fuchsiaprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConFuchsiaPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_FUCHSIA, str);
#endif
    }

    inline void cyanprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConCyanPrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_CYAN, str);
#endif
    }

    inline void whiteprnt(char const *str)
    {
        assert(NULL != str);
#ifdef __PLATF_WINDOWS
        WinConWhitePrint(str);
#else
    acolprnt(ANSI_NULL, ANSI_FG_WHITE, str);
#endif
    }

#ifdef __cplusplus
}
#endif

#endif /* _ANSICOL_H */