import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-advertisement',
  imports: [],
  templateUrl: './advertisement.component.html',
  styleUrl: './advertisement.component.scss',
})
export class AdvertisementComponent {
  @Input() position: 'left' | 'right' = 'left';

  ads: string[] = ['assets/ads/ad1.jpg', 'assets/ads/ad2.jpg', 'assets/ads/ad3.jpg'];

  currentAd: string = '';

  ngOnInit() {
    this.showRandomAd();
    setInterval(() => this.showRandomAd(), 10000); // Change ad every 10 seconds
  }

  showRandomAd() {
    const randomIndex = Math.floor(Math.random() * this.ads.length);
    this.currentAd = this.ads[randomIndex];
  }
}
