import scrapy
import logging
logging.getLogger('scrapy').setLevel(logging.WARNING)


class spider1(scrapy.Spider):
    name = 'Wikipedia'
    start_urls = ['https://en.wikipedia.org/wiki/Battery_(electricity)']

    def parse(self, response): 
        print(' '.join(response.css('div#mw-content-text>div>p')[0].css('::text').extract()))
