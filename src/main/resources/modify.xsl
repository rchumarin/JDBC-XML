<xsl:stylesheet version = "1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">     
    <xsl:template match="/"> 
        <entries>                             
            <xsl:for-each select="entries/entry">
                <entry>
                    <xsl:attribute name="field"> 
                        <xsl:value-of select="field"/>                         
                    </xsl:attribute> 
                </entry>
            </xsl:for-each>            
        </entries>        
    </xsl:template>
</xsl:stylesheet>


<!--<xsl:stylesheet version = "1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">     
    <xsl:output method="xml" indent="yes"/> 
    <xsl:template match="/">
        
        <entries>                             
            <xsl:for-each select="entries/entry">
                <xsl:text disable-output-escaping = "yes">&#13;&#10;</xsl:text>              
                <xsl:text disable-output-escaping = "yes">&lt;entry field="</xsl:text> 
                <xsl:value-of select="field"/> 
                <xsl:text disable-output-escaping = "yes">"&gt;</xsl:text>                                                      
            </xsl:for-each> 
            <xsl:text disable-output-escaping = "yes">&#13;&#10;</xsl:text>           
        </entries>        
    </xsl:template>
</xsl:stylesheet>-->

