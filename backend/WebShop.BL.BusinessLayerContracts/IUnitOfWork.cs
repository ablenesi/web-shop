namespace WebShop.BL.BusinessLayerContracts
{
    public interface IUnitOfWork
    {
        IProductOperations ProductOperations { get; }

        void SaveChanges();
    }
}
