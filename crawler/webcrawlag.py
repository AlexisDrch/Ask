import sys
import urllib
import urllib.request


from urllib.request import FancyURLopener
class MyOpener(FancyURLopener):
    version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'
    
def crawlit():
    myopener = MyOpener();
    page = myopener.open("https://www.amazon.com/s/ref=nb_sb_ss_c_1_13?url=search-alias%3Daps&field-keywords=surfboard&sprefix=magic+bullet+%2Caps%2C188&crid=1V7SH1J00R4PN")
    contents = page.read()
    print(contents)
    
if __name__ == "__main__":
    crawlit()