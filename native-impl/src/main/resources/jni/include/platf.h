/**
 * Created at 2020/7/19 14:36.
 * 
 * @author Liangcheng Juves
 */
#ifndef _PLATF_H
#define _PLATF_H

#ifdef __cplusplus
extern "C"
{
#endif

#if defined(_AIX) || defined(__TOS_AIX__)
#define __PLATF_AIX
#endif

#ifdef UTS
#define __PLATF_UTS
#endif

#if defined(AMIGA) || defined(__amigaos__)
#define __PLATF_AMIGAOS
#endif

#ifdef aegis
#define __PLATF_AEGIS
#endif

#ifdef apollo
#define __PLATF_APOLLO
#endif

#ifdef __BEOS__
#define __PLATF_BEOS
#endif

#if defined(__bg__) || defined(__bgq__) || defined(__THW_BLUEGENE__) || defined(__TOS_BGQ__)
#define __PLATF_BLUE_GENE
#endif

#if defined(__FreeBSD__) || defined(__NetBSD__) || defined(__OpenBSD__) || defined(__bsdi__) || defined(__DragonFly__) || defined(_SYSTYPE_BSD)
#define __PLATF_BSD_ENV
#endif

#ifdef __bsdi__
#define __PLATF_BSDOS
#endif

#ifdef __convex__
#define __PLATF_CONVEX
#endif

#ifdef __CYGWIN__
#define __PLATF_CYGWIN_ENV
#endif

#if defined(DGUX) || defined(__DGUX__) || defined(__dgux__)
#define __PLATF_DGUX
#endif

#ifdef __DragonFly__
#define __PLATF_DRAGONFLY
#endif

#if defined(_SEQUENT_) || defined(sequent)
#define __PLATF_DYNIX_PTX
#endif

#ifdef __ECOS
#define __PLATF_ECOS
#endif

#ifdef __EMX__
#define __PLATF_EMX_ENV
#endif

#if defined(__FreeBSD__) || defined(__FreeBSD_kernel__)
#define __PLATF_FREEBSD
#endif

#if defined(__GNU__) || defined(__gnu_hurd__)
#define __PLATF_GNU_HURD
#endif

#if defined(__FreeBSD_kernel__) && defined(__GLIBC__)
#define __PLATF_GNU_KFREEBSD
#endif

#if defined(__gnu_linux__)
#define __PLATF_GNU_LINUX
#endif

#ifdef __hiuxmpp
#define __PLATF_HIUXMPP
#endif

#if defined(_hpux) || defined(hpux) || defined(__hpux)
#define __PLATF_HPUX
#endif

#ifdef __OS400__
#define __PLATF_IBMOS_400
#endif

#ifdef __INTEGRITY
#define __PLATF_INTEGRITY
#endif

#ifdef __INTERIX
#define __PLATF_INTERIX_ENV
#endif

#if defined(sgi) || defined(__sgi)
#define __PLATF_IRIX
#endif

#if defined(__linux__) || defined(linux) || defined(__linux)
#define __PLATF_LINUX
#ifdef __ANDROID__
#define __PLATF_ANDROID
#endif /* __ANDROID__ */
#endif

#ifdef __Lynx__
#define __PLATF_LYNX
#endif

#if defined(macintosh) || defined(Macintosh) || (defined(__APPLE__) && defined(__MACH__))
#define __PLATF_MACOS
#endif

#if defined(__OS9000) && defined(_OSK)
#define __PLATF_MICROWAREOS_9
#endif

#ifdef __minix
#define __PLATF_MINIX
#endif

#ifdef __MORPHOS__
#define __PLATF_MORPHOS
#endif

#if defined(mpeix) || defined(__mpexl)
#define __PLATF_MPEIX
#endif

#if defined(MSDOS) || defined(__MSDOS__) || defined(_MSDOS) || defined(__DOS__)
#define __PLATF_MSDOS
#endif

#ifdef __NetBSD__
#define __PLATF_NETBSD
#endif

#ifdef __TANDEM
#define __PLATF_NONSTOP
#endif

#ifdef __nucleus__
#define __PLATF_NUCLEUS_RTOS
#endif

#ifdef __OpenBSD__
#define __PLATF_OPENBSD
#endif

#if defined(OS2) || defined(_OS2) || defined(__OS2__) || defined(__TOS_OS2__)
#define __PLATF_OS2
#endif

#ifdef __palmos__
#define __PLATF_PALMOS
#endif

#ifdef EPLAN9
#define __PLATF_PLAN9
#endif

#ifdef pyr
#define __PLATF_PYR
#endif

#if defined(__QNX__) || defined(__QNXNTO__)
#define __PLATF_QNX
#endif

#ifdef sinux
// Reliant UNIX
#define __PLATF_SINUX
#endif

#if defined(M_I386) || defined(M_XENIX) || defined(_SCO_DS)
// SCO OpenServer
#define __PLATF_SCO_OPENSERVER
#endif

#if defined(sun) || defined(__sun)
#if defined(__SVR4) || defined(__svr4__)
/* Solaris */
#define __PLATF_SOLARIS
#else
/* SunOS */
#define __PLATF_SUNOS
#endif
#endif

#ifdef __VOS__
#define __PLATF_STRATUS_VOS
#endif

#if defined(__sysv__) || defined(__SVR4) || defined(__svr4__) || defined(_SYSTYPE_SVR4)
#define __PLATF_SVR4_ENV
#endif

#ifdef __SYLLABLE__
#define __PLATF_SYLLABLE
#endif

#if defined(__osf__) || defined(__osf)
/* Tru64 (OSF/1) */
#define __PLATF_OSF
#endif

#if defined(ultrix) || defined(__ultrix) || defined(__ultrix__) || (defined(unix) && defined(vax))
#define __PLATF_ULTRIX
#endif

#ifdef _UNICOS
#define __PLATF_UNICOS
#endif

#if defined(_CRAY) || defined(__crayx1)
/* UNICOS/mp */
#define __PLATF_UNICOS
#endif

#if defined(__unix__) || defined(__unix)
#define __PLATF_UNIX_ENV
#endif

#if defined(sco) || defined(_UNIXWARE7)
#define __PLATF_UWINWARE
#endif

#ifdef _UWIN
/* U/Win Environment */
#define __PLATF_UWIN_ENV
#endif

#if defined(__VMS) || defined(VMS)
#define __PLATF_VMS
#endif

#if defined(__VXWORKS__) || defined(__vxworks)
#define __PLATF_VXWORKS
#endif

#if defined(_WIN16) || defined(_WIN32) || defined(_WIN64) || defined(__WIN32__) || defined(__TOS_WIN__) || defined(__WINDOWS__) || defined(_WIN32_WCE)
#define __PLATF_WINDOWS
#ifdef _WIN32_WCE
/* Windows CE */
#define __PLATF_WINCE
#endif /* _WIN32_WCE */
#endif

#ifdef _WINDU_SOURCE
/* Wind/U Environment */
#define __PLATF_WINDU_SOURCE
#endif

#if defined(__MVS__) || defined(__HOS_MVS__) || defined(__TOS_MVS__)
#define __PLATF_ZOS
#endif

#ifndef __32bits
#define __32bits sizeof((void *)0) == 4
#endif

#ifndef __64bits
#define __64bits sizeof((void *)0) == 8
#endif

#ifdef __cplusplus
}
#endif

#endif /* _PLATF_H */
