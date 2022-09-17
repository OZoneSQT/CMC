using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LastCompiler.Util
{
    internal class SourceHandler
    {
        public static char EOL = '\n';
        public static char EOT = '0';

        Queue srcBuffer = new Queue();

        public SourceHandler(string path, string filename)
        {
            try
            {

            } 
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());

                Thread.Sleep(10000);
                Environment.Exit(1);
            }
        }

    }
}
