using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LastCompiler.Scanner
{
    internal class TokenKind
    {
        private String? spelling = null;

        private TokenKind()
        {
        }

        private TokenKind(String? spelling)
        {
            this.spelling = spelling;
        }

        public String? getSpelling()
        {
            return spelling;
        }
    }
}
