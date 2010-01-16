\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{ppjdoc}

\DeclareOption*{\PassOptionsToClass{\CurrentOption}{report}}

\ProcessOptions

\LoadClass[11pt, onecolumn]{report}

\RequirePackage[a4paper, left=3.5cm, right=3cm, bottom=3cm, top=3cm]{geometry}
\RequirePackage[utf8]{inputenc}
\RequirePackage[croatian]{babel}
\RequirePackage[T1]{fontenc}
\RequirePackage{graphicx}
\RequirePackage{enumitem}
\RequirePackage{booktabs}

% Da bi se promjenio stil citiranja umjesto:
% [authoryear, round]
% staviti:
% [numbers, square]
\RequirePackage[authoryear, round]{natbib}
\RequirePackage{amsmath}
\RequirePackage{subfig}
\RequirePackage{fixltx2e}
\RequirePackage[outsidefoot]{pageno}
\RequirePackage{caption}
\RequirePackage{ifthen}
\RequirePackage[nottoc]{tocbibind}
\RequirePackage{enumitem}
\RequirePackage{fullpage}
\RequirePackage{float}
\RequirePackage{listings}
%\RequirePackage{courier}

\renewcommand{\captionlabelfont}{\bfseries}
\renewcommand{\captionfont}{\small}

% 1.5 prored, 1.3 je faktor za množenje
\linespread{1.2}

\addto\captionscroatian{%
	\def\bibname{Literatura}
}

\renewcommand{\labelitemi}{\textendash}
\renewcommand{\labelitemii}{\textbullet}

\renewcommand\thechapter{\@arabic\c@chapter.}
\renewcommand\thesection{\thechapter\@arabic\c@section.}
\renewcommand\thesubsection{\thesection\@arabic\c@subsection.}
\renewcommand\thesubsubsection{\thesubsection\@arabic\c@subsubsection.}

\renewcommand\theequation{\ifnum \c@chapter>\z@ \thechapter\fi \@arabic\c@equation}
\renewcommand\thefigure{\ifnum \c@chapter>\z@ \thechapter\fi \@arabic\c@figure}
\renewcommand\thetable{\ifnum \c@chapter>\z@ \thechapter\fi \@arabic\c@table}

\renewcommand\tableofcontents{%
   \setcounter{page}{1}
   \chapter*{\contentsname
   \@mkboth{%
   \MakeUppercase\contentsname}{\MakeUppercase\contentsname}}%
   \@starttoc{toc}%
}

\renewcommand{\thepage}{\roman{page}}

\newboolean{atappendix}
\setboolean{atappendix}{false}
\newboolean{secondpart}
\setboolean{secondpart}{false}

\renewcommand{\appendix}{
	\setboolean{atappendix}{true}
	\setcounter{chapter}{0}
	\renewcommand{\thechapter}{\Alph{chapter}}
}

\renewcommand{\@makechapterhead}[1]{%
\ifthenelse{\boolean{atappendix}}{
	\vspace*{50\p@}%
	{\setlength{\parskip}{0em} \parindent \z@ \raggedright \normalfont
		\interlinepenalty\@M
		\ifnum \c@secnumdepth >\m@ne
			\Huge\bfseries Dodatak \thechapter \\ #1
			\par\nobreak
		\fi
		\nobreak
		\vskip 40\p@
	}
}
{
	\ifthenelse{\boolean{secondpart}}
	{
	}
	{
		\setcounter{page}{1}
		\renewcommand{\thepage}{\arabic{page}}
		\setboolean{secondpart}{true}
	}

	\vspace*{50\p@}%
	{\setlength{\parskip}{0em} \parindent \z@ \raggedright \normalfont
		\interlinepenalty\@M
		\ifnum \c@secnumdepth >\m@ne
			\Huge\bfseries \thechapter~#1
			\par\nobreak
		\fi
		\nobreak
		\vskip 40\p@
	}
}
}

\renewcommand{\@makeschapterhead}[1]{%
\ifthenelse{\boolean{atappendix}}{
	\vspace*{50\p@}%
	{\setlength{\parskip}{0em} \parindent \z@ \raggedright
		\normalfont
		\interlinepenalty\@Mdef
		\Huge\scshape Dodatak \\ #1\par
		\nobreak
		\vskip 40\p@
	}
}
{
	\vspace*{50\p@}%
	{\setlength{\parskip}{0em} \parindent \z@ \raggedright
		\normalfont
		\interlinepenalty\@M
		\Huge\scshape #1\par
		\nobreak
		\vskip 40\p@
	}
}
}

\newcommand{\posveta}[1]{
	\newpage
	\thispagestyle{empty}
	\vspace*{\fill}
	\hfil#1\hfil
	\vspace*{\fill}
}


\def\grupa#1{\gdef\@grupa{#1}}
\def\@grupa{\@latex@warning@no@line{Grupa nije definirana.
Koristi \noexpand\grupa za definiranje}}

\def\datumpredaje#1{\gdef\@datumpredaje{#1}}
\def\@datumpredaje{\@latex@warning@no@line{Datum predaje nije definiran.
Koristi \noexpand\datumpredaje za definiranje}}

\if@titlepage
	\renewcommand\maketitle{\begin{titlepage}%
	\let\footnotesize\small
	\let\footnoterule\relax

	\begin{center}
		{\large SVEU\v{C}ILI\v{S}TE U ZAGREBU}
		
		{\large\bfseries FAKULTET ELEKTROTEHNIKE I RA\v{C}UNARSTVA}
	\end{center}
	\vfill
	\begin{center}%
		{\Large Prevođenje programskih jezika \par}%
		\vskip 2em%
		{\huge \bfseries \@title \par}%
		\vskip 1em%
		{\normalsize \@grupa \par}%
		\vskip 1em%
		{\large\lineskip .75em%
		\begin{tabular}[t]{c}%
			\@author
		\end{tabular}\par}%
	\end{center}\par
	\vfill
	\begin{center}

		{\normalsize Zagreb, \@datumpredaje \par}%

	\end{center}
	\end{titlepage}%
	\setcounter{footnote}{0}%
	\global\let\maketitle\relax
	\global\let\@thanks\@empty
	\global\let\@author\@empty
	\global\let\@date\@empty
	\global\let\author\relax
	\global\let\date\relax
	\global\let\and\relax
	}
\fi

\newdimen\listindentation
\setlength{\listindentation}{3 em}

\renewenvironment{itemize}%
{
	\setlength{\parskip}{0em}
	\advance\@itemdepth\@ne
	\edef\@itemitem{labelitem\romannumeral\@itemdepth}%

	\begin{list}{\csname\@itemitem\endcsname}{\setlength{\leftmargin}{\listindentation}\setlength{\itemsep}{0 em}\setlength{\topsep}{0 em}}
}%
{\end{list}}

\newcommand{\engl}[1]{(engl.~\emph{#1})}

\endinput
