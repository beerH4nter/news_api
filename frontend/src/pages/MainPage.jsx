import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../styles/MainPage.css";

const API_URL = "http://localhost:8080";

const MainPage = () => {
    const [newsList, setNewsList] = useState([]);

    useEffect(() => {
        fetch(`${API_URL}/news`)
            .then((res) => res.json())
            .then(async (data) => {
                const enriched = await Promise.all(
                    data.map(async (news) => {
                        const firstImagePath = news.imagePathList[0];
                        if (firstImagePath) {
                            return { ...news, img: firstImagePath};
                        }
                        return { ...news, img: null };
                    })
                );
                setNewsList(enriched);
            });
    }, []);

    return (
        <div className="main-page">
            {newsList.map((news) => (
                <div key={news.id} className="news-card">
                    {news.img && (
                        <>
                            <div className="main-page_img-container">
                                <img src={`${API_URL}/image/${news.img}`} alt="news" />
                            </div>
                        </>
                    )}
                    <Link to={`/news/${news.id}`} className="news-title">
                        {news.title}
                    </Link>
                </div>
            ))}
        </div>
    );
};

export default MainPage;
