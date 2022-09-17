using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace LastCompiler.Models
{
    /**+
     * Use [EnumMember(Value = "declare")] to define an enum with an string value
     * https://stackoverflow.com/questions/8588384/how-to-define-an-enum-with-string-value
     * https://learn.microsoft.com/en-us/dotnet/api/system.runtime.serialization.enummemberattribute?view=net-6.0
     */
    internal enum EToken
    {
        IDENTIFIER,
        INTEGERLITERAL,
        OPERATOR,

        [EnumMember(Value = "declare")]
        DECLARE,

        [EnumMember(Value = "do")]
        DO,

        [EnumMember(Value = "else")]
        ELSE,

        [EnumMember(Value = "fi")]
        FI,

        [EnumMember(Value = "func")]
        FUNC,

        [EnumMember(Value = "if")]
        IF,

        [EnumMember(Value = "od")]
        OD,

        [EnumMember(Value = "return")]
        RETURN,

        [EnumMember(Value = "say")]
        SAY,

        [EnumMember(Value = "then")]
        THEN,

        [EnumMember(Value = "var")]
        VAR,

        [EnumMember(Value = "while")]
        WHILE,

        [EnumMember(Value = ",")]
        COMMA,

        [EnumMember(Value = ";")]
        SEMICOLON,

        [EnumMember(Value = "(")]
        LEFTPARAN,

        [EnumMember(Value = )]
        RIGHTPARAN,

        EOT,

        ERROR
    }
}
