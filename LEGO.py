import scrapy
import logging
from scrapy import Selector

class rei(scrapy.Spider):
    name="nameofsomething"
    start_urls = ['https://www.rei.com/product/880840/rei-co-op-trail-40-pack-womens']
    
    def parse(self, response):
        self.logger.info('Parse function called on %s', response.url)
        logging.log(logging.DEBUG, "Debug message")
        logging.debug("Debug message")
        print(response.xpath('//body').extract())
        self.logger.info('A response from %s just arrived!', response.url)
        
        
        
        
        
        #website: https://www.amazon.com/Coleman-2000007827-Sundome-4-Person-Green/dp/B004J2GUOU/ref=sr_1_3?s=outdoor-recreation&ie=UTF8&qid=1521467426&sr=1-3&keywords=tents
        #response.xpath('//div[@id="cerberus-data-metrics"]/@data-asin-price').extract() on the website
        #response.xpath('//div[@id="imgTagWrapperId"]/img/@alt').extract()
        #['https://images-na.ssl-images-amazon.com/images/I/41oQKECQ7oL._SY300_QL70_.jpg']
        
        
        
        
        
        
        
        
        
        
        #https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=tents
        
        
        
#if __name__ == "__main__":
#    parse()